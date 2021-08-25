package main

import (
	"fmt"
	"runtime"
	"sync"
)

var count int
var wg sync.WaitGroup
var mu sync.Mutex

const total = 20

func main() {
	// recria condicao de corrida - corrige com mutex
	wg.Add(total)
	for i := 0; i < total; i++ {
		go func() {
			mu.Lock()
			v := count
			// faz yield
			runtime.Gosched()
			v++
			count = v
			fmt.Println("i=", i, "v=", v)
			mu.Unlock()
			wg.Done()
		}()
	}
	wg.Wait()
	fmt.Println("resultado: ", count)

}

func exec(number int) {

}
