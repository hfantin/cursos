package main

import "fmt"

type pessoa struct {
	nome string
}

func (p *pessoa) falar() {
	fmt.Println(p.nome, "falou")
}

func dizerAlgumaCoisa(h humanos) {
	h.falar()
}

type humanos interface {
	falar()
}

func main() {
	p := pessoa{nome: "hamilton"}
	dizerAlgumaCoisa(&p)
}
