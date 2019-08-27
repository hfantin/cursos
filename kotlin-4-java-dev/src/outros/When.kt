package outros

class When {

    fun teste(grade: Int) {
        when (grade) {
            in 0..4 -> println("$grade Reprovado")
            in 5 until 7 -> println("$grade Recuperação")
            in 7 until 10 -> println("$grade Aprovado")
            10 -> println("$grade Aprovado com honras.")
            else -> println("$grade Aluno esquisito.")
        }
    }
}

fun main() {
    When().teste(0)
    When().teste(1)
    When().teste(2)
    When().teste(3)
    When().teste(4)
    When().teste(5)
    When().teste(6)
    When().teste(7)
    When().teste(8)
    When().teste(9)
    When().teste(10)
    When().teste(11)

}