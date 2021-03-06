package v1.main

import v1.model.funcionario.Analista
import v1.model.funcionario.Diretor
import v1.model.funcionario.Gerente

fun testaFuncionarios() {


    val funcionario = Analista(
        nome = "com.github.hfantin.modelo.Analista",
        cpf = "111.111.111-11",
        salario = 1000.0
    )

    val gerente = Gerente(
        nome = "com.github.hfantin.modelo.Gerente",
        cpf = "222.222.2222-22",
        salario = 2000.0,
        senha = 1234
    )

    val diretor = Diretor(
        nome = "com.github.hfantin.modelo.Diretor",
        cpf = "333.333.333-33",
        salario = 4000.0,
        senha = 4000,
        plr = 200.0
    )

    val calculadora = Calculadora()
    calculadora.registra(funcionario)
    calculadora.registra(gerente)
    calculadora.registra(diretor)

    println("$funcionario bonus=${funcionario.bonificacao}")
    println("$gerente bonus=${gerente.bonificacao} autenticado=${gerente.autenticacao(1234)}")
    println("$diretor bonus=${diretor.bonificacao} autenticado=${diretor.autenticacao(1234)}")

    println("total de bonificações=${calculadora.total}")
}