package main

import (
	"fmt"
)

type Estado struct {
	nome      string
	populacao int
	capital   string
}

func main() {

	capitais := map[string]string{
		"GO": "Goiânia",
		"PB": "João Pessoa",
		"PR": "Curitiba"}
	fmt.Println(len(capitais))
	fmt.Println("inicia...: ", capitais)
	capitais["SP"] = "Sao Paulo"
	fmt.Println("inclui sp: ", capitais)
	capitais["GO"] = "GOAINIA 2"
	fmt.Println("atualiza: ", capitais)
	fmt.Println("não existe: ", capitais["RN"])
	_, encontrado := capitais["RJ"]
	fmt.Println("encontrado: ", encontrado)
	delete(capitais, "PB")
	fmt.Println("apaga PB: ", capitais)

	for sigla, estado := range capitais {
		fmt.Printf("%s nome %s .\n", sigla, estado)
	}

	estados := make(map[string]Estado, 6)
	estados["GO"] = Estado{"Goiás", 6434052, "Goiânia"}
	estados["PR"] = Estado{"Paraná", 10997462, "Curitiba"}
	estados["SE"] = Estado{"Sergipe", 2228489, "Aracaju"}

	for sigla, estado := range estados {
		fmt.Printf("%s (%s) possui %d habitantes.\n",
			estado.nome, sigla, estado.populacao)
	}

}
