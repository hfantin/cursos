package ex7

class Retangulo(private val largura: Double, private val altura: Double) : Shape {
    override fun getArea() = largura * altura
}