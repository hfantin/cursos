package com.github.hfantin.teste

import com.github.hfantin.modelo.Cliente
import com.github.hfantin.modelo.ContaCorrente
import com.github.hfantin.modelo.ContaPoupanca

fun testaContasDiferentes() {
    val contaCorrente = ContaCorrente(
        titular = Cliente(nome = "Alex", cpf = "", senha = 1),
        numero = 1000
    )
    val contaPoupanca = ContaPoupanca(
        titular = Cliente(nome = "Fran", cpf = "", senha = 2),
        numero = 1001
    )

    contaCorrente.deposita(1000.0)
    contaPoupanca.deposita(1000.0)

    println("saldo corrente: ${contaCorrente.saldo}")
    println("saldo poupança: ${contaPoupanca.saldo}")

    contaCorrente.saca(100.0)
    contaPoupanca.saca(100.0)

    println("saldo após saque corrente: ${contaCorrente.saldo}")
    println("saldo após saque poupança: ${contaPoupanca.saldo}")

    contaCorrente.transfere(100.0, contaPoupanca, 1)

    println("saldo corrente após transferir para poupança: ${contaCorrente.saldo}")
    println("saldo poupança após receber transferência: ${contaPoupanca.saldo}")

    contaPoupanca.transfere(200.0, contaCorrente, 2)

    println("saldo poupança após transferir para corrente: ${contaPoupanca.saldo}")
    println("saldo corrente após receber transferência: ${contaCorrente.saldo}")
}