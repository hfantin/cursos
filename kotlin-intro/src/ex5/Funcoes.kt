package ex5

fun soma(a: Int, b: Int) : Int{
    return a + b
}

fun multiplicacao(a: Int, b: Int) = a * b

fun printSoma(a: Int, b: Int) {
    println("$a + $b = ${soma(a, b)}")
}

fun printMultiplicacao(a: Int, b: Int): Unit = println("$a * $b = ${multiplicacao(a, b)}")

fun media(vararg numeros: Int): Float {
    if(numeros.isNotEmpty()){
        val total = numeros.sum()
        return total / numeros.size.toFloat()

    }
    return 0F
}

fun printNome(nome:String="Anomimo"){
    println(nome)
}

fun main() {
    println(soma(2, 3))
    println(multiplicacao(2, 3))
    printSoma(2, 3)
    printSoma(b=10, a=5)
    printMultiplicacao(2, 3)

    println(media(1, 2, 3, 4))
    printNome()
    printNome("Hamilton")
}