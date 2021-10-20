package main

import (
	"fmt"
	"sync"
)

var wg sync.WaitGroup

func main() {
	wg.Add(2)
	go print("> a")
	go print("- b")
	wg.Wait()
}

func print(value string) {
	for i := 0; i < 1000; i++ {
		fmt.Printf("%s | %d\n", value, i)
	}
	wg.Done()
}
