package ex11

val adicao: (Int, Int) -> Int = { a: Int, b: Int -> a + b}
val subtracao = { a: Int, b: Int -> a - b}

fun calcular(a: Int, b: Int, calc: (Int, Int) -> Int) = calc.invoke(a, b)

fun outraFuncao(a: Int, b: Int) : Int {
    return (a + a) * ( b + b )
}

fun main() {
    println(adicao(5, 4))
    println(subtracao(10, 9))
    println(calcular(6, 7, adicao))
    println(calcular(100, 57, subtracao))

    //
    val resultado = calcular(100, 1000) {
        a, b -> a * b
    }
    println("resultado = $resultado")

    //

    val res2 = calcular(5, 6, ::outraFuncao)
    println("res2= $res2")
}