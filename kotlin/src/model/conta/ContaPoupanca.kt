package model.conta

class ContaPoupanca(
    titular: String,
    conta: Int
) : Conta(
    titular = titular,
    conta = conta
) {

    override fun sacar(valor: Double) {
        println("sacando $valor")
        if (valor <= 0.0)
            throw IllegalArgumentException("o valor do saque deve ser maior que zero")
        if (valor > saldo) {
            throw Exception("Saldo insuficiente")
        }
        saldo -= valor
    }

    override fun toString(): String {
        return "ContaPoupanca() ${super.toString()}"
    }
}