package main

import (
	"fmt"
)

func main() {
	c := gen()
	receive(c)

	fmt.Println("about to exit")
}

func gen() <-chan int {
	c := make(chan int)

	go func() {
		defer close(c)
		for i := 0; i < 100; i++ {
			c <- i
		}
	}()

	return c
}

func receive(ch <-chan int) {
	for c := range ch {
		fmt.Println("valor recebido no canal: ", c)
	}

}
