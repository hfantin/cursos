package model.conta

class ContaCorrente(
    titular: String,
    conta: Int

) : Conta(
    titular = titular,
    conta = conta
) {


    override fun sacar(valor: Double) {
        val valorComTaxa = valor + 0.1
        println("sacando $valorComTaxa")
        if (valorComTaxa <= 0.0)
            throw IllegalArgumentException("o valor do saque deve ser maior que zero")
        if (valorComTaxa > saldo) {
            throw Exception("Saldo insuficiente")
        }
        saldo -= valorComTaxa
    }

    override fun toString(): String {
        return "ContaCorrente() ${super.toString()}"
    }


}