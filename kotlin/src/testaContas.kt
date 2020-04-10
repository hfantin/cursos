import model.conta.Conta
import model.conta.ContaCorrente
import model.conta.ContaPoupanca

fun testaContas() {
    val corrente = ContaCorrente("Tosco", 1000)
    val poupanca = ContaPoupanca("Teste", 2000)


    corrente.depositar(1000.0)
    poupanca.depositar(1000.0)


    println("saldo corrente: ${corrente.saldo}")
    println("saldo poupanca: ${poupanca.saldo}")

    corrente.sacar(100.0)
    poupanca.sacar(100.0)

    println("saldo corrente apos saque: ${corrente.saldo}")
    println("saldo poupanca apos saque: ${poupanca.saldo}")

    corrente.transfere(100.0, poupanca)
    println("saldo corrente apos transferir para poupanca: ${corrente.saldo}")
    println("saldo poupanca apos receber transferencia de corrente: ${poupanca.saldo}")

    poupanca.transfere(100.0, corrente)
    println("saldo poupanca apos transferir para poupanca: ${poupanca.saldo}")
    println("saldo corrente apos receber transferencia de corrente: ${corrente.saldo}")






}