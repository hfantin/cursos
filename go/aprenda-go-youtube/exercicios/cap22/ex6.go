// - Escreva um programa que coloque 100 números em um canal, retire os números do canal, e demonstre-os.

package main

import "fmt"

func main() {
	ch := make(chan int)
	go func() {
		for i := 1; i < 101; i++ {
			ch <- i
		}
		close(ch)
	}()
	for v := range ch {
		fmt.Println(v)
	}
}
