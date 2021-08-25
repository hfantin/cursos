package main

import (
	"fmt"
	"runtime"
	"sync"
)

var count int
var wg sync.WaitGroup

const total = 50

func main() {
	// recria condicao de corrida
	wg.Add(total)
	count = 0
	for i := 0; i < total; i++ {
		go exec(i)
	}
	wg.Wait()
	fmt.Println("resultado: ", count)

}

func exec(number int) {
	i := count
	// faz yield
	runtime.Gosched()
	fmt.Println("number=", number, "count=", i)
	i++
	count = i
	wg.Done()
}
