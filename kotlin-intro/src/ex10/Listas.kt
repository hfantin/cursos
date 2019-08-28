package ex10

fun main() {
    val lista = listOf(1, 2, 3)
    println("****** lista ***********")
    println("lista $lista")
    println("vazia ${lista.isEmpty()}")
    println("tamanho ${lista.size}")
    println("elemento[1]=${lista[1]}")

    val listaMutavel = mutableListOf(1, 2, 3, 4, 5, 6)
    println("****** lista mutavel ***********")
    println("lista $listaMutavel")
    listaMutavel[0] = -1
    println("lista $listaMutavel")
    listaMutavel.add(10)
    listaMutavel += 11
    println("lista $listaMutavel")
    listaMutavel.removeAt(0)
    listaMutavel.remove(6)
    println("lista remove 6 $listaMutavel")

    println("vazia ${listaMutavel.isEmpty()}")
    println("tamanho ${listaMutavel.size}")
    println("elemento[1]=${listaMutavel[1]}")
}