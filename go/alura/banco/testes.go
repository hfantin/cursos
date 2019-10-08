package main

import "fmt"

type ContaCorrente struct {
	titular       string
	numeroAgencia int
	numeroConta   int
	saldo         float64
}

func main() {
	//
	contaDoJovem := ContaCorrente{titular: "Jovem",
		numeroAgencia: 589, numeroConta: 123456, saldo: 125.5}

	contaDoCampeao := ContaCorrente{"Campeão", 222, 111222, 200}

	fmt.Println(contaDoJovem)
	fmt.Println(contaDoCampeao)

	// outra forma de usar o struct
	var contaDoGuerreiro *ContaCorrente
	contaDoGuerreiro = new(ContaCorrente)
	contaDoGuerreiro.titular = "Guerreiro"

	// retorna o endereço
	fmt.Println(contaDoGuerreiro)
	// retorna a referencia
	fmt.Println(*contaDoGuerreiro)

	// outra forma
	// var contaDoFera *ContaCorrente = new(ContaCorrente)
	// fmt.Println(contaDoFera)

	// comparando

	contaDoCampeao2 := ContaCorrente{"Campeão", 222, 111222, 200}
	fmt.Println("compara contas do campeão:", contaDoCampeao == contaDoCampeao2)

	var contaDoGuerreiro2 *ContaCorrente
	contaDoGuerreiro2 = new(ContaCorrente)
	contaDoGuerreiro2.titular = "Guerreiro"
	// essa comparacao é entre os endereços  retorna false
	fmt.Println("compara endereços das contas do guerreiro:", contaDoGuerreiro2 == contaDoGuerreiro, &contaDoGuerreiro, "comparando com", &contaDoGuerreiro2)
	fmt.Println("compara contas do guerreiro:", *contaDoGuerreiro2 == *contaDoGuerreiro)

}
