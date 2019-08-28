package ex7

interface Shape {
    fun getArea() : Double
    fun printArea() {
        val formatada = "%.2f".format(getArea())
        println("${javaClass.simpleName} area: $formatada")
    }
}