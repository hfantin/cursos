package com.github.hfantin.teste

import com.github.hfantin.modelo.*

fun testaObjects() {
    // teste de object expression
    val fran = object : Autenticavel {
        val nome: String = "Fran"
        val cpf: String = "111.111.111-11"
        val senha: Int = 1000

        override fun autentica(senha: Int) = this.senha == senha
    }

    val sistemaInterno = SistemaInterno()
    sistemaInterno.entra(fran, 1000)
    println("nome do cliente ${fran.nome}")

    //
    val conta = ContaCorrente(
        titular = Cliente(
            nome = "Tosco",
            cpf = "",
            endereco = Endereco(),
            senha = 1
        ), numero = 100
    )
    println("total ${Conta.total}")
}