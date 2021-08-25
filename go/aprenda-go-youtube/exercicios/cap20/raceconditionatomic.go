package main

import (
	"fmt"
	"runtime"
	"sync"
	"sync/atomic"
)

var count int32
var wg sync.WaitGroup

const total = 50

func main() {
	// recria condicao de corrida - corrige com atomic
	wg.Add(total)
	count = 0
	for i := 0; i < total; i++ {
		go exec()
	}
	wg.Wait()
	fmt.Println("resultado: ", count)
}

func exec() {
	atomic.AddInt32(&count, 1)
	// faz yield
	runtime.Gosched()
	atomic.LoadInt32(&count)
	wg.Done()
}
