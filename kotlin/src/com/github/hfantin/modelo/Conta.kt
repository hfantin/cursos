package com.github.hfantin.modelo

import com.github.hfantin.exception.FalhaAutenticacaoException
import com.github.hfantin.exception.SaldoInsuficienteException


abstract class Conta(
    var titular: Cliente,
    val numero: Int
): Autenticavel by titular {
    companion object {
        var total = 0
            private set
    }

    init {
        total++
    }

/*  padrao delegate - Com apenas a syntax Autenticavel by titular, é feita a delegação
   A restrição nesse caso é que precisamos operar com properties val, pois não é possível modificar a implementação depois de criarmos uma conta.
    override fun autentica(senha: Int): Boolean {
        return titular.autentica(senha)
    }
*/

    var saldo = 0.0
        protected set

    fun deposita(valor: Double) {
        if (valor > 0) {
            this.saldo += valor
        }
    }

    abstract fun saca(valor: Double)

    @Throws(SaldoInsuficienteException::class)
    fun transfere(valor: Double, destino: Conta, senha: Int) {
        if (saldo < valor) {
            throw SaldoInsuficienteException()
        }
        if(!autentica(senha)) {
            throw FalhaAutenticacaoException()
        }

        saldo -= valor
        destino.deposita(valor)
    }
}