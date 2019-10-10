package main

import (
	"fmt"

	t "github.com/hfantin/banco/clientes"
	c "github.com/hfantin/banco/contas"
)

func PagarBoleto(conta verificarConta, valor float64) {
	fmt.Println("pagando boleto no valor", valor)
	conta.Sacar(valor)
}

type verificarConta interface {
	Sacar(valor float64) (string, float64)
}

func main() {
	jovem := t.Titular{"Jovem", "191", "Dev"}
	contaDoJovem := c.ContaCorrente{Titular: jovem,
		NumeroAgencia: 589, NumeroConta: 123}
	contaDoJovem.Depositar(5000)

	guerreiro := t.Titular{"Guerreiro", "272", "DBA"}
	contaDoGuerreiro := c.ContaCorrente{Titular: guerreiro,
		NumeroAgencia: 589, NumeroConta: 456}
	contaDoGuerreiro.Depositar(4000)

	// testes de operações na conta do jovem
	fmt.Println("conta do jovem:", contaDoJovem)
	fmt.Println("conta do guerreiro:", contaDoGuerreiro)
	fmt.Println(contaDoJovem.Sacar(300))
	fmt.Println(contaDoJovem.Sacar(-600))
	fmt.Println(contaDoJovem.Depositar(-100))
	fmt.Println(contaDoJovem.Depositar(100))
	// transferencia
	fmt.Println(contaDoJovem, contaDoGuerreiro)
	fmt.Println(contaDoJovem.Transferir(159.79, &contaDoGuerreiro))
	fmt.Println(contaDoJovem, contaDoGuerreiro)

	// interface
	bigode := t.Titular{"Bigode", "323", "Mestre Cervejeiro"}
	contaDoBigode := c.ContaPoupanca{Titular: bigode,
		NumeroAgencia: 999, NumeroConta: 777, Operacao: 1}
	contaDoBigode.Depositar(456.93)

	fmt.Println(contaDoBigode)

	// pagar boletos
	fmt.Println("\nPagar boletos: \n")
	fmt.Println("saldo do guerreiro antes", contaDoGuerreiro.ObterSaldo())
	PagarBoleto(&contaDoGuerreiro, 32.79)
	fmt.Println("saldo do guerreiro", contaDoGuerreiro.ObterSaldo())
	fmt.Println("saldo do bigode antes", contaDoBigode.ObterSaldo())
	PagarBoleto(&contaDoBigode, 47.33)
	fmt.Println("saldo do bigode", contaDoBigode.ObterSaldo())

}
