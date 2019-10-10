package contas

import (
	"fmt"

	"github.com/hfantin/banco/clientes"
)

type ContaPoupanca struct {
	Titular                              clientes.Titular
	NumeroAgencia, NumeroConta, Operacao int
	saldo                                float64
}

// (c *ContaPoupanca) esse ponteiro é semelhante ao this de algumas linguagens -
func (c *ContaPoupanca) Sacar(valor float64) (string, float64) {
	fmt.Println("sacar ", valor)
	podeSacar := valor > 0 && valor <= c.saldo
	if podeSacar {
		c.saldo -= valor
		return "saque realizado com sucesso!", c.saldo
	} else {
		return "sem saldo ou valor inválido!", c.saldo
	}

}

func (c *ContaPoupanca) Depositar(valor float64) (string, float64) {
	fmt.Println("depositar ", valor)
	podeDepositar := valor > 0
	if podeDepositar {
		c.saldo += valor
		return "deposito realizado com sucesso!", c.saldo
	} else {
		return "valor inválido!", c.saldo
	}

}

func (c *ContaPoupanca) Transferir(valor float64, conta *ContaPoupanca) bool {
	fmt.Println("Transferir ", valor, "para", conta.Titular)
	if valor > 0 && valor <= c.saldo {
		c.saldo -= valor
		conta.Depositar(valor)
		return true
	}
	return false
}

func (c *ContaPoupanca) ObterSaldo() float64 {
	return c.saldo
}
