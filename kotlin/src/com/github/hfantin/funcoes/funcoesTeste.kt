package com.github.hfantin.funcoes


public fun testaFuncoes() {
    testaTipoFuncaoReferencia()

    testaTipoFuncaoClasse()

    testaFuncaoLambda()

    testaFuncaoAnonima()

    val calculaBonificacao: (salario: Double) -> Double = lambda@{ salario ->
        if (salario < 1000.0) {
            return@lambda salario + 50
        }
        salario + 100
    }

    println("calculaBonificacao de 1000: ${calculaBonificacao(1000.0)}")
    println("calculaBonificacao de 999.99: ${calculaBonificacao(999.99)}")


    val calculaBonificacaoFuncaoAnonima: (salario: Double) -> Double = fun(salario): Double {
        if (salario < 1000.0) {
            return salario + 50
        }
        return salario + 100
    }
    println("calculaBonificacaoFuncaoAnonima de 1000: ${calculaBonificacaoFuncaoAnonima(1000.0)}")
    println("calculaBonificacaoFuncaoAnonima de 999.99: ${calculaBonificacaoFuncaoAnonima(999.99)}")
}

private fun testaFuncaoAnonima() {
//    val somaFuncaoAnonima: (Int, Int) -> Int = fun(a, b) : Int {
    val somaFuncaoAnonima = fun(a: Int, b: Int): Int {
        return a + b
    }
    println("somaFuncaoAnonima: ${somaFuncaoAnonima(4, 5)}")
}

private fun testaFuncaoLambda() {
    //    val somaLambda: (Int, Int) -> Int = {a, b ->
    val somaLambda = { a: Int, b: Int ->
        a + b
    }
    println("somaLambda: ${somaLambda(2, 3)}")
}

private fun testaTipoFuncaoClasse() {
    val somaClasse: (Int, Int) -> Int = Soma()
    println("somaClasse ${somaClasse(10, 10)}")

    val somaClasse2 = Soma2()
    println("somaClasse2: ${somaClasse2(13, 2)}")
}

private fun testaTipoFuncaoReferencia() {
    // val minhaFuncao: () -> Unit =  :: teste
    val soma = ::soma
    println("soma: " + soma(5, 3))
}

fun soma(a: Int, b: Int): Int {
    return a + b
}

class Soma : (Int, Int) -> Int {
    override fun invoke(a: Int, b: Int): Int = a + b
}

// Podemos também acionar o invoke diretamente na classe usando operator, conforme abaixo
class Soma2 {
    operator fun invoke(a: Int, b: Int): Int {
        return a + b
    }
}