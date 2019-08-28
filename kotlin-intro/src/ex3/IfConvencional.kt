package ex3


fun main(args: Array<String>) {
    parOuImpar(1)
    parOuImpar(2)

}

private fun parOuImpar(numero: Int) {
    if (numero % 2 == 0) {
        println("$numero é par")
    } else {
        println("$numero é impar")
    }
}