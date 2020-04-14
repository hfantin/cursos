package com.github.hfantin.teste

import com.github.hfantin.modelo.Cliente
import com.github.hfantin.modelo.ContaCorrente
import com.github.hfantin.modelo.ContaPoupanca

fun testaCopiasEReferencias() {
    val numeroX = 10
    var numeroY = numeroX
    numeroY++

    println("numeroX $numeroX")
    println("numeroY $numeroY")

    val contaJoao = ContaCorrente(Cliente(nome = "João", cpf = "", senha = 1), 1002)
    contaJoao.titular.nome = "João"
    var contaMaria = ContaPoupanca(Cliente(nome = "Maria", cpf = "", senha = 2), 1003)
    contaMaria.titular.nome = "Maria"
    contaJoao.titular.nome = "João"

    println("titular conta joao: ${contaJoao.titular}")
    println("titular conta maria: ${contaMaria.titular}")

    println(contaJoao)
    println(contaMaria)
}