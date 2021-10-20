package main

import (
	"fmt"
	"runtime"
	"sync"
	"time"
)

var wg sync.WaitGroup

func main() {

	fmt.Println("num CPU: ", runtime.NumCPU())
	wg.Add(2)
	go func1()
	go func2()
	fmt.Println("num goroutine: ", runtime.NumGoroutine())
	wg.Wait()
}

func func1() {
	for i := 0; i < 100; i++ {
		fmt.Println("- func 1", i)
		time.Sleep(200)
	}
	wg.Done()
}

func func2() {
	for i := 0; i < 100; i++ {
		fmt.Println("> func 2", i)
		time.Sleep(250)
	}
	wg.Done()
}
