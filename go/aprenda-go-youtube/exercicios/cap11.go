package main

import "fmt"

type pessoa struct {
	nome      string
	sobrenome string
	sorvetes  []string
}

func main() {
	fmt.Println("exercicios do capitulo 11 - structs")
	hamilton := pessoa{"hamilton", "fantin", []string{"chocolate"}}
	mari := pessoa{"mari", "goes", []string{"morango"}}
	for _, v := range hamilton.sorvetes {
		fmt.Println(v)
	}

	for _, v := range mari.sorvetes {
		fmt.Println(v)
	}

}
