package model.conta

abstract class Conta(
    private val titular: String,
    private val conta: Int
) {

    var saldo: Double = 0.0 // set(valor) { if (valor>0) field = valor}
        protected set    // private set -- torna o set private

    abstract fun sacar(valor: Double)

    fun depositar(valor: Double) {
        println("depositando $valor")
        if (valor <= 0.0)
            throw IllegalArgumentException("o valor do saque deve ser maior que zero")
        saldo += valor
    }

    fun transfere(valor: Double, destino: Conta) {
        if (this == destino) {
            throw Exception("Não é possível transferir para conta de origem")
        }
        try {
            this.sacar(valor)
            destino.depositar(valor)
        } catch (e: Exception) {
            println("não foi possivel efetuar a transferencia: ${e.message}")
        }

    }

    override fun toString(): String {
        return "model.conta.Conta(titular='$titular', conta=$conta, saldo=$saldo)"
    }


}