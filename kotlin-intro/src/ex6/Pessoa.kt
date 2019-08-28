package ex6

import java.time.LocalDate

class Pessoa(val nome: String, private val anoNascimento: Int) {

    init {
        println("Iniciando pessoa com nome $nome $anoNascimento")
    }

    constructor(nome: String, anoNascimento: Int, cpf: String): this(nome, anoNascimento) {
        this.cpf = cpf
    }

    var cpf: String = "191"
        private set // atribuição apenas na classe

    val idade: Int
        get() = LocalDate.now().year - anoNascimento

    override fun toString(): String {
        return "Pessoa(nome='$nome', anoNascimento=$anoNascimento, cpf='$cpf')"
    }


}

fun main(args: Array<String>) {

    val chuckNorris = Pessoa("chuck norris", 1960)
    val sergioMalandro = Pessoa("sergio malandro",  1965, "273")

    println(chuckNorris)
    println(chuckNorris.idade)
    println(sergioMalandro)
    println(sergioMalandro.idade)



}