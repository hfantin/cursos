package colecoes.listas

data class Prateleira(
    val genero: String,
    val livros: List<Livro>
) {
    val organizarPorAutor get() = livros.sortedBy { it.autor }
    val organizarPorAnoPublicacao get() = livros.sortedBy { it.anoPublicacao }
}