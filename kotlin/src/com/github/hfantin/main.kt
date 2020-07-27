package com.github.hfantin

import com.github.hfantin.modelo.Autenticavel
import com.github.hfantin.modelo.Endereco
import com.github.hfantin.modelo.SistemaInterno


fun main() {
//    var enderecoNulo: Endereco? = Endereco(logradouro = "Rua vergueiro", complemento = "123")
//    var logradouro: String? = enderecoNulo?.logradouro
//    enderecoNulo?.let {
//        println(it.logradouro.length)
//        val tamanhoComplemento = it.complemento?.length ?: throw IllegalArgumentException("Complementeo não pode ser vazio")
//        println("tamanhoComplemento $tamanhoComplemento")
//
//    }

//    testaFuncoes()
    Endereco(logradouro = "Rua vergueiro", numero = 1234)
        .let {
            "${it.logradouro}, ${it.numero}".toUpperCase()
        }.let(::println)

    listOf(Endereco(complemento = "casa"), Endereco(), Endereco(complemento = "apartamento"))
        .filter { it.complemento?.isNotEmpty() ?: false }
            
        .let(::println)


    soma(2, 3) {
        println(it)
    }

    val autenticavel = object : Autenticavel {
        val senha = 1234
        override fun autentica(senha: Int) = this.senha == senha
    }

    SistemaInterno().entra(autenticavel, 1234, autenticado = {
        println("realizar pagamento")
    })
}

private fun soma(a: Int, b: Int, resultado: (Int) -> Unit) {
    resultado(a + b)
}
