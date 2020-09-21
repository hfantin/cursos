package colecoesparte2

fun main() {
    val bancodNomes = BancoNomes()
    bancodNomes.salva("Hamilton")
    println(bancodNomes.nomes)
    println(BancoNomes().nomes)
}

class BancoNomes {

    val nomes: Collection<String> get() = dados.toList()

    fun salva(nome: String) {
        dados.add(nome)
    }

    companion object {
        private val dados = mutableListOf<String>()
    }

}