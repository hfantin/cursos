package outros

val name: String = "World"

fun topLeveFunction() : String {
    return "hello $name"
}
class Funcoes {
    fun menberFunction() : String {
        return topLeveFunction()
    }
}

fun main() {
    val f = Funcoes()
    println(f.menberFunction())
}