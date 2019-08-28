package ex8

class Cao : Animal() {
    override val som: String = "au"

    fun au() {
        println("${javaClass.simpleName} diz auuu")
    }
}