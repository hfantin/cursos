package hamilton.com.br.financask

import hamilton.com.br.financask.model.Transacao

/**
 * Created by hamilton on 13/12/17.
 */
class TransacaoDAO {

    val transacoes: List<Transacao> = Companion.transacoes
    companion object {
        private val transacoes: MutableList<Transacao> = mutableListOf()

    }

    fun adiciona(transacao: Transacao){
        Companion.transacoes.add(transacao)
    }

    fun altera(transacao: Transacao, posicao: Int){
        Companion.transacoes[posicao] = transacao
    }

    fun remove(posicao: Int){
        Companion.transacoes.removeAt(posicao)
    }



}