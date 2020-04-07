import java.lang.IllegalArgumentException

fun main() {
    println("Bem vindo ao bytebank")
//    testaLacos()
    val conta1 = Conta("Teste 1", 1000, 1000.0)
    val conta2 = Conta("Teste 2", 2000, 1000.0)


    conta1.depositar(1000.0)
    conta2.sacar(900.0)
    conta2.transfere(1.0, conta1)

    println(conta1)
    println(conta2)


    var teste = Conta2("Tosco", 1222, 0.2)
    teste.saldo = 0.1
    println(teste)

}

class Conta2(
    private val titular: String,
    private val conta: Int

) {
    // construtor secundario
    constructor(titular: String, conta: Int, saldo: Double) : this(titular, conta) {
        println("construindo conta2")
        this.saldo = saldo
    }

    init {
        println("inicializando conta2")
    }
    var saldo: Double = 0.0
        set(value) {
            if (value > 0.0) field = value
        }
        get // redundante

    override fun toString(): String {
        return "Conta2(titular='$titular', conta=$conta saldo=$saldo)"
    }


}

data class Conta(
    private val titular: String,
    private val conta: Int,
    var saldo: Double = 0.0 // set(valor) { if (valor>0) field = valor}
    // private set -- torna o set private - so funciona em classe normal
) {
    fun sacar(valor: Double) {
        println("sacando $valor")
        if (valor <= 0.0)
            throw IllegalArgumentException("o valor do saque deve ser maior que zero")
        if (valor > saldo) {
            throw Exception("Saldo insuficiente")
        }
        saldo -= valor
    }

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
}

private fun testaLacos() {
    // for (i in 5 downTo 1) - inverso
    // for (i in 5 downTo 1 step 2) - inverso pulando os pares
    for (i in 1..5) {
        val titular = "tosco $i"
        val conta = 1000 + i
        var saldo = i + 10.0
        println("titular $titular")
        println("conta  $conta")
        println("saldo $saldo")
        testaCondicoes(saldo)
        println()
    }
}

private fun testaCondicoes(saldo: Double) {
    /*
    if (saldo > 0.0) {
        println("saldo é positivo")
    } else if(saldo == 0.0) {
        println("saldo é neutro")
    } else {
        println("saldo é negativo")
    }
    */
    when {
        saldo > 0.0 -> println("saldo é positivo")
        saldo == 0.0 -> println("saldo é neutro")
        else -> println("saldo é negativo")
    }
}