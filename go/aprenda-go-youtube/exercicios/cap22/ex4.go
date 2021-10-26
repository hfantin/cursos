package main

import (
	"fmt"
)

// ..use um select statement para demonstrar os valores do canal.
func main() {
	q := make(chan int)
	c := gen(q)

	receive(c, q)

	fmt.Println("about to exit")
}

func gen(q chan<- int) <-chan int {
	c := make(chan int)
	go func() {
		for i := 0; i < 100; i++ {
			c <- i
		}
		close(c)
		q <- 0
	}()
	return c
}

func receive(ch <-chan int, q chan int) {
	for {
		select {
		case c := <-ch:
			fmt.Println("o valor de c é", c)
		case <-q:
			return
		}
	}

}
