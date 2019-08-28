package ex8

class Gato : Animal() {
    override val som: String = "miau"

    fun miau() {
        println("${javaClass.simpleName} diz miauuuu")
    }
}