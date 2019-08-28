package ex9

data class Humano(val nome: String, val anoNascimento: Int, val cpf: String)

fun main(){
    val h1 = Humano("jovem", 70, "1")
    val h2 = Humano("jovem", 70, "1")
    val h3 = Humano("campeao", 71, "2")

    println(h1 == h2)
    println(h2 == h3)
    println(h1 == h3)
}
