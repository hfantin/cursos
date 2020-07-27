package com.github.hfantin.teste

import com.github.hfantin.exception.FalhaAutenticacaoException
import com.github.hfantin.exception.SaldoInsuficienteException
import com.github.hfantin.modelo.Cliente
import com.github.hfantin.modelo.ContaCorrente
import com.github.hfantin.modelo.ContaPoupanca

fun testaComportamentosConta() {
    val contaAlex = ContaCorrente(titular = Cliente(nome = "Alex", cpf = "", senha = 1), numero = 1000)
    contaAlex.deposita(200.0)

    val contaFran = ContaPoupanca(numero = 1001, titular = Cliente(nome = "Fran", cpf = "", senha = 1))
    contaFran.deposita(300.0)

    println(contaFran.titular)
    println(contaFran.numero)
    println(contaFran.saldo)

    println(contaAlex.titular)
    println(contaAlex.numero)
    println(contaAlex.saldo)

    println("depositando na conta do Alex")
    contaAlex.deposita(50.0)
    println(contaAlex.saldo)

    println("depositando na conta da Fran")
    contaFran.deposita(70.0)
    println(contaFran.saldo)

    println("sacando na conta do Alex")
    contaAlex.saca(250.0)
    println(contaAlex.saldo)

    println("sacando na conta da Fran")
    contaFran.saca(100.0)
    println(contaFran.saldo)

    println("saque em excesso na conta do Alex")
    contaAlex.saca(100.0)
    println(contaAlex.saldo)

    println("saque em excesso na conta da Fran")
    contaFran.saca(500.0)
    println(contaFran.saldo)

    println("Transferência da conta da Fran para o Alex")

    try {
        contaFran.transfere(destino = contaAlex, valor = 100.0, senha = 3)
        println("Transferência sucedida")
    } catch (e: SaldoInsuficienteException) {
        println("Saldo insuficiente")
        e.printStackTrace()
    } catch (e: FalhaAutenticacaoException) {
        println("Falha na autenticacao")
        e.printStackTrace()
    } catch (e: Exception) {
        println("Erro desconhecido")
        e.printStackTrace()
    }

    println(contaAlex.saldo)
    println(contaFran.saldo)
}