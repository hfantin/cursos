package main

import (
	"fmt"
)

// - ...demonstre o comma ok idiom.
func main() {
	c := make(chan int)

	go func() {
		c <- 1
	}()
	v, ok := <-c
	fmt.Println(v, ok)

	close(c)

	v, ok = <-c
	fmt.Println(v, ok)
}
