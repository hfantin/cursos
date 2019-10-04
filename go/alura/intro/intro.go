package main

import (
	"fmt"
	"reflect"
)

func main() {
	var comando int

	// testes
	fmt.Println("endereco: ", &comando)
	fmt.Println("comando tipo", reflect.TypeOf(comando))

	// menu

	fmt.Println("1- Iniciar Monitoramento")
	fmt.Println("2- Exibir Logs")
	fmt.Println("0- Sair do Programa")

	// obtem comando
	fmt.Scan(&comando) // enviar endereço da variavel com &
	// fmt.Scanf("%d", &comando) // alternativa enviando o tipo
	fmt.Println("O valor da variável é:", comando)
}
