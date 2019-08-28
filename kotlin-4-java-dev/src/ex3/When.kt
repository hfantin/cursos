package ex3

fun teste(grade: Int) {
    when (grade) {
        in 0..4 -> println("$grade Reprovado")
        in 5 until 7 -> println("$grade Recuperação")
        in 7 until 10 -> println("$grade Aprovado")
        10 -> println("$grade Aprovado com honras.")
        else -> println("$grade Aluno esquisito.")
    }
}

fun main() {
    teste(0)
    teste(1)
    teste(2)
    teste(3)
    teste(4)
    teste(5)
    teste(6)
    teste(7)
    teste(8)
    teste(9)
    teste(10)
    teste(11)

}