package ex4

fun main() {

    // intervalo inclusivo
    println("intervalo inclusivo")
    for(i in 1..10)
        println("- numero $i")

    // intervalo exclusivo
    println("intervalo exclusivo")
    for(i in 1 until 10)
        println("- numero $i")

    // intervalo com incremento
    println("intervalo exclusivo")
    for(i in 1..10 step 2)
        println("- numero $i")

    // intervalo com incremento
    println("ordem inversa")
    for(i in 10 downTo 1)
        println("- numero $i")

}