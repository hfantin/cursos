package main

import (
	"fmt"

	c "github.com/hfantin/banco/contas"
)

func main() {
	contaDoJovem := c.ContaCorrente{Titular: "Jovem",
		NumeroAgencia: 589, NumeroConta: 123, Saldo: 5000}

	contaDoGuerreiro := c.ContaCorrente{Titular: "Guerreiro",
		NumeroAgencia: 589, NumeroConta: 456, Saldo: 4500}

	// testes de operações na conta do jovem
	fmt.Println("conta do jovem:", contaDoJovem)
	fmt.Println("conta do guerreiro:", contaDoJovem)
	fmt.Println(contaDoJovem.Sacar(300))
	fmt.Println(contaDoJovem.Sacar(-600))
	fmt.Println(contaDoJovem.Depositar(-100))
	fmt.Println(contaDoJovem.Depositar(100))
	// transferencia
	fmt.Println(contaDoJovem, contaDoGuerreiro)
	fmt.Println(contaDoJovem.Transferir(159.79, &contaDoGuerreiro))
	fmt.Println(contaDoJovem, contaDoGuerreiro)
}
