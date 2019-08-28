package ex4

fun main() {

    val brejas = arrayOf("heineken", "madalena", "la trape")
    // percorrer array
    for(breja in brejas)
        println("- $breja")
    // percorrer array com indice
    for((i, v) in brejas.withIndex())
        println("- $i - $v")
    // percorrer array invertido
    for(breja in brejas.reversedArray())
        println("- $breja")
    // forEach em ranges
    (1..10).forEach { println(it) }

}