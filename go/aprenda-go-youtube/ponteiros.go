package main

import "fmt"

type pessoa struct {
	nome      string
	sobrenome string
	idade     int
}

func main() {
	x := 50
	fmt.Println("endereço de x", &x)
	incrementaPonteiro(&x)
	fmt.Println("na main ", x)
	slice := []int{1, 2, 3}
	appendPonteiro(&slice)
	fmt.Println("slice na main ", slice)

	p := pessoa{nome: "Hamilton", sobrenome: "Fantin", idade: 42}
	mudarPessoa(&p)
	fmt.Println("pessoa ", p)

}

func incrementaPonteiro(x *int) {
	*x++
	fmt.Println("na funcao ", *x)
}

func appendPonteiro(slice *[]int) {
	*slice = append(*slice, 4, 5, 6)
	fmt.Println("slice na funcao ", *slice)
}

func mudarPessoa(p *pessoa) {
	(*p).nome = "100%"
	p.sobrenome = "naval"
}
