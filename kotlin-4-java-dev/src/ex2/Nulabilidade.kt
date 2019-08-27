package ex2

class Nulabilidade {

//    val naoNulo: String = null // ->  não compila
    val naoNulo: String = "valor não pode ser nulo"
    val nulo: String? = null

    fun imprimir() {
//        println(nulo.length) // -> não compila
        println(nulo?.length) // -> imprime nulo
    }

    fun imprimir2() {
        if(nulo!=null) {
            println(nulo.length) // -> aqui não precisa do ?.
        } else {
            println("eh nulo")
        }

    }

    fun imprimir3() {
        // igual ao if da função imprimir2()
        println(nulo?.length?:"valor é nulo")
    }

}
fun main(args: Array<String>) {
    val nulabilidade = Nulabilidade()
    nulabilidade.imprimir()
    nulabilidade.imprimir2()
    nulabilidade.imprimir3()
}