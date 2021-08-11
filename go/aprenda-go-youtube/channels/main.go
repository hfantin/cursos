package main

import "fmt"

func main() {
	fmt.Println("Channels - loop and close ")
	c := make(chan int)
	go moveToChannel(10, c)
	readFromChannel(c)
}

func moveToChannel(t int, c chan<- int) {
	for i := 0; i < t; i++ {
		fmt.Printf("> moving %d to channel...\n", i)
		c <- i
	}
	close(c)
}

func readFromChannel(c <-chan int) {
	fmt.Println("reading from channel...")
	for r := range c {
		fmt.Printf("< reading %d from channel\n", r)
	}
}
