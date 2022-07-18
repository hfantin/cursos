package main

import (
	"fmt"
	"math/rand"
	"sync"
	"time"
)

func main() {
	responseChannel := make(chan string)
	fmt.Println("simula chamada http")
	var wg sync.WaitGroup
	for i := 0; i < 10; i++ {
		wg.Add(1)
		go simulateHttpCall(i, responseChannel, &wg)
	}
	go func() {
		wg.Wait()
		close(responseChannel)
	}()
	readFromChannel(responseChannel)

	fmt.Println("fim")
}

func generateRandomTimeout() int {
	seed := rand.NewSource(time.Now().UnixNano())
	random := rand.New(seed)
	return random.Intn(3000)
}

func simulateHttpCall(number int, responseChannel chan<- string, wg *sync.WaitGroup) {
	defer wg.Done()
	timeout := generateRandomTimeout()
	fmt.Printf("chamando #%d com timeout de %d\n", number, timeout)
	time.Sleep(time.Duration(timeout) * time.Millisecond)
	responseChannel <- fmt.Sprintf("resposta da chamada # %d", number)
	// close(responseChannel)
}

func readFromChannel(responseChannel <-chan string) {
	fmt.Println("lendo do canal resposta...")
	for r := range responseChannel {
		fmt.Printf("- resposta:  %s\n", r)
	}
}
