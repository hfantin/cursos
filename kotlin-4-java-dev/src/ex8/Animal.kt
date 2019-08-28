package ex8

abstract class Animal {

    protected abstract val som: String

    fun fazerSom() {
        println("${javaClass.simpleName} fala $som")
    }
}