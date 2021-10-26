package main

import "fmt"

func main() {
	fmt.Println("ex 1 do captilo 22")
	c := make(chan int) // com buffe: c := make(chan int, 1)

	go func() {
		fmt.Println(<-c)
	}()
	c <- 42
	close(c)

}
