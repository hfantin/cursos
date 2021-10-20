package main

import "fmt"

func main() {
	fmt.Println("calculo do fatorial")
	x := calcular(50)
	fmt.Println(x)

}

func calcular(x int) int {
	if x == 1 {
		return 1
	}
	return x * calcular(x-1)
}
