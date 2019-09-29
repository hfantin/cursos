package main

import (
	"fmt"
	"os"
	"strconv"
)

func main() {
	entrada := os.Args[1:]
	numeros := make([]int, len(entrada))
	for i, n := range entrada {
		numero, err := strconv.Atoi(n)
		if err != nil {
			fmt.Printf("%s não é um número válido!\n", n)
			os.Exit(1)
		}
		numeros[i] = numero
	}
	fmt.Println("lista ordenada: ", quicksort(numeros))

}

func quicksort(numeros []int) []int {
	if len(numeros) <= 1 {
		return numeros
	}
	// cria uma copia da lista original
	n := make([]int, len(numeros))
	copy(n, numeros)
	// elegemos um pivo - nesse caso o elemento que se encontra mais ao centro da lista
	indicePivo := len(n) / 2
	pivo := n[indicePivo]
	// agora removemos o pivo da lista original
	// o primeiro argumento fatia o slice do primeiro elemento até o indice do pivo
	// e o segundo do indice do pivo + 1 até o final
	// as reticencias no final informam que todos os elementos do segundo slice devem ser adicionados ao slice base
	n = append(n[:indicePivo], n[indicePivo+1:]...) // remove o pivo da lista
	menores, maiores := particionar(n, pivo)
	fmt.Println("pivo: ", pivo)
	fmt.Println("menores: ", menores)
	fmt.Println("maiores: ", maiores)
	return append(
		append(quicksort(menores), pivo),
		quicksort(maiores)...)
}

func particionar(numeros []int, pivo int) (menores []int, maiores []int) {
	for _, n := range numeros {
		if n <= pivo {
			menores = append(menores, n)
		} else {
			maiores = append(maiores, n)
		}
	}
	return menores, maiores
}
