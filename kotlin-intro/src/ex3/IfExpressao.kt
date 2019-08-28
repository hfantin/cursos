package ex3


fun main(args: Array<String>) {
    val numero = 1
    val paridade = parOuImpar(numero)
    println("$numero é $paridade")

}

private fun parOuImpar(numero: Int) = if (numero % 2 == 0) "par" else "impar"

