package contas

import "fmt"

type ContaCorrente struct {
	Titular       string
	NumeroAgencia int
	NumeroConta   int
	Saldo         float64
}

// (c *ContaCorrente) esse ponteiro é semelhante ao this de algumas linguagens -
func (c *ContaCorrente) Sacar(valor float64) (string, float64) {
	fmt.Println("sacar ", valor)
	podeSacar := valor > 0 && valor <= c.Saldo
	if podeSacar {
		c.Saldo -= valor
		return "saque realizado com sucesso!", c.Saldo
	} else {
		return "sem saldo ou valor inválido!", c.Saldo
	}

}

func (c *ContaCorrente) Depositar(valor float64) (string, float64) {
	fmt.Println("depositar ", valor)
	podeDepositar := valor > 0
	if podeDepositar {
		c.Saldo += valor
		return "deposito realizado com sucesso!", c.Saldo
	} else {
		return "valor inválido!", c.Saldo
	}

}

func (c *ContaCorrente) Transferir(valor float64, conta *ContaCorrente) bool {
	fmt.Println("Transferir ", valor, "para", conta.Titular)
	if valor > 0 && valor <= c.Saldo {
		c.Saldo -= valor
		conta.Depositar(valor)
		return true
	}
	return false
}
