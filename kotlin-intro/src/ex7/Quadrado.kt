package ex7

class Quadrado(private val lado: Double) : Shape {
    override fun getArea() = lado * lado
}