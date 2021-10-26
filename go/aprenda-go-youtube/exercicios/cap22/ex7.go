package main

import "fmt"

// - Crie um programa que lance 10 goroutines onde cada uma envia 10 números a um canal;
// - Tire estes números do canal e demonstre-os.
func main() {
	ch := make(chan int)
	for i := 0; i < 10; i++ {
		go func() {
			for j := 0; j < 10; j++ {
				ch <- j
			}
		}()
	}
	for v := range ch {
		fmt.Println(v)
	}
}
