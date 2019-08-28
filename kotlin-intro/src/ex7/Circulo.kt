package ex7

class Circulo(private val raio: Double) : Shape {
    override fun getArea() = Math.PI * raio * raio
}