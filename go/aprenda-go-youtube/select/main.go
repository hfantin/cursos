package main

import (
	"fmt"
)

//        - Chans par, ímpar, quit
//        - Func send manda números pares pra um canal, ímpares pra outro, e fecha/quit
//        - Func receive é um select entre os três canais, encerra no quit

func main() {

	par := make(chan int)
	ímpar := make(chan int)
	quit := make(chan bool)

	go enviaNumeros(par, ímpar, quit)

	recebeNumeros(par, ímpar, quit)
}

func enviaNumeros(par, ímpar chan int, quit chan bool) {
	total := 10
	for i := 0; i < total; i++ {
		if i%2 == 0 {
			par <- i
		} else {
			ímpar <- i
		}
	}
	close(par)
	close(ímpar)
	quit <- true
}

func recebeNumeros(par, ímpar chan int, quit chan bool) {
	for {
		select {
		case v := <-par:
			fmt.Println("O número", v, "é par.")
		case v := <-ímpar:
			fmt.Println("O número", v, "é ímpar.")
		case <-quit:
			return
		}
	}
}
