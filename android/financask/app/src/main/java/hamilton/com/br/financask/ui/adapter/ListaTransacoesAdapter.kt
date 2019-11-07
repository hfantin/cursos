package hamilton.com.br.financask.ui.adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import hamilton.com.br.financask.R
import hamilton.com.br.financask.extensions.formataParaBrasileiro
import hamilton.com.br.financask.extensions.limitaEmAte
import hamilton.com.br.financask.model.Tipo
import hamilton.com.br.financask.model.Transacao
import kotlinx.android.synthetic.main.transacao_item.view.*

class ListaTransacoesAdapter(val transacoes: List<Transacao>,
                             val context: Context) : BaseAdapter() {

    companion object {
        private val LIMITE_CATEGORIA = 12
    }

    override fun getView(posicao: Int, view: View?, parent: ViewGroup?): View {
        val viewCriada = LayoutInflater.from(context).inflate(R.layout.transacao_item, parent, false)
        val transacao = getItem(posicao)
        adicionaValor(transacao, viewCriada)
        adicionaIcone(transacao, viewCriada)
        adicionaCategoria(viewCriada, transacao)
        adicionaData(viewCriada, transacao)
        return viewCriada
    }

    private fun adicionaValor(item: Transacao, viewCriada: View) {
        val cor: Int = corPor(item.tipo)
        viewCriada.transacao_valor.setTextColor(cor)
        viewCriada.transacao_valor.text = item.valor.formataParaBrasileiro()
    }

    private fun corPor(tipo: Tipo): Int {
        if (tipo == Tipo.RECEITA) {
            return ContextCompat.getColor(context, R.color.receita)
        }
        return ContextCompat.getColor(context, R.color.despesa)
    }

    private fun adicionaIcone(item: Transacao, viewCriada: View) {
        val icone: Int = iconePor(item.tipo)
        viewCriada.transacao_icone.setBackgroundResource(icone)
    }

    private fun iconePor(tipo: Tipo): Int {
        if (tipo == Tipo.RECEITA) {
            return R.drawable.icone_transacao_item_receita
        }
        return R.drawable.icone_transacao_item_despesa
    }

    private fun adicionaCategoria(viewCriada: View, item: Transacao) {
        viewCriada.transacao_categoria.text = item.categoria.limitaEmAte(LIMITE_CATEGORIA)
    }

    private fun adicionaData(viewCriada: View, item: Transacao) {
        viewCriada.transacao_data.text = item.data.formataParaBrasileiro()
    }

    override fun getItem(posicao: Int) = transacoes[posicao]
    override fun getItemId(posicao: Int) = 0L
    override fun getCount() = transacoes.size
}