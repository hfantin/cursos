package main

import "fmt"

/**
https://programming.guide/go/three-dots-ellipsis.html
Operador ellipsis (...)
usado em quatro situações distintas:
1) Funções com argumentos variaveis:
	func Sum(nums ...int) int {
		res := 0
		for _, n := range nums {
			res += n
		}
		return res
	}
2) Passar argumentos para funcoes:
	primes := []int{2, 3, 5, 7}
	fmt.Println(Sum(primes...)) // 17

3) Especificar tamanho de array:
	stooges := [...]string{"Moe", "Larry", "Curly"}
	// len(stooges) == 3

4) coringa para descrever listas de pacotes
	go test ./...

*/
func main() {

	fmt.Println("testes com slices")
	original := []int{1, 2, 3, 4, 5}
	fmt.Println("Original:", original)
	// faz pega referencia do slice original
	novo := original[1:3]
	fmt.Println("Novo:", novo)
	// altera nos dois slices
	original[2] = 13
	fmt.Println("Original pós modificação:", original)
	fmt.Println("Novo pós modificação:", novo)

	// Inserir valores
	fmt.Println("Inserindo valores: ")
	s := make([]int, 0)
	s = append(s, 23)
	fmt.Println(s)

	fmt.Println("insere 22 no começo do slice: ")
	s2 := []int{23, 24, 25}
	n := []int{22}
	fmt.Println("slice original: ", s2)
	s2 = append(n, s2...)
	fmt.Println("slice modificado: ", s2)

	fmt.Println("insere 14 e 15 no meio do slice: ")
	s3 := []int{11, 12, 13, 16, 17, 18}
	fmt.Println("slice original: ", s3)

	v := []int{14, 15}
	s3 = append(s3[:3], append(v, s3[3:]...)...)
	fmt.Println("slice modificado: ", s3)

	fmt.Println("remover 20 do inicio da lista: ")
	s4 := []int{20, 30, 40, 50, 60}
	fmt.Println("lista: ", s4)
	s4 = s4[1:]
	fmt.Println("lista depois: ", s4)

	fmt.Println("remover 50 e 60 do fim da lista: ")
	s5 := []int{20, 30, 40, 50, 60}
	fmt.Println("lista: ", s5)
	s5 = s5[:len(s5)-2]
	fmt.Println("lista depois: ", s5)

	fmt.Println("remover 30 e 40 da lista ")
	s6 := []int{20, 30, 40, 50, 60}
	fmt.Println("lista: ", s6)
	s6 = append(s6[:1], s6[3:]...)
	fmt.Println("lista depois: ", s6)

	fmt.Println("copiando slices ")
	numeros := []int{1, 2, 3, 4, 5}
	dobros := make([]int, len(numeros))
	copy(dobros, numeros) // copy(destino, origem)
	for i := range dobros {
		dobros[i] *= 2
	}
	fmt.Println(numeros)
	fmt.Println(dobros)

}
