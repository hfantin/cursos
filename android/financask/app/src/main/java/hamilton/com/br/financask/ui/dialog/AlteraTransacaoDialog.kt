package hamilton.com.br.financask.ui.dialog

import android.content.Context
import android.view.ViewGroup
import hamilton.com.br.financask.R
import hamilton.com.br.financask.extensions.formataParaBrasileiro
import hamilton.com.br.financask.model.Tipo
import hamilton.com.br.financask.model.Transacao

/**
 * Created by hamilton on 05/12/17.
 */
class AlteraTransacaoDialog(view: ViewGroup,
                            private val context: Context) : FormularioTransacaoDialog(context, view) {
    override val tituloBotaoPositivo: String
        get() = "Alterar"

    override fun tituloPor(tipo: Tipo): Int {
        if (tipo == Tipo.RECEITA) {
            return R.string.altera_receita
        }
        return R.string.altera_despesa
    }

    fun chama(transacao: Transacao, delegate: (transacao: Transacao) -> Unit) {
        val tipo = transacao.tipo
        super.chama(tipo, delegate)
        inicializaCampos(transacao)
    }

    private fun inicializaCampos(transacao: Transacao) {
        val tipo = transacao.tipo
        inicializaCampoValor(transacao)
        inicializaCampoData(transacao)
        inicializaCampoCategoria(tipo, transacao)
    }

    private fun inicializaCampoCategoria(tipo: Tipo, transacao: Transacao) {
        val categoriasRetornadas = context.resources.getStringArray(categoriasPor(tipo))
        val posicaoCategoria = categoriasRetornadas.indexOf(transacao.categoria)
        campoCategoria.setSelection(posicaoCategoria, true)
    }

    private fun inicializaCampoData(transacao: Transacao) {
        campoData.setText(transacao.data.formataParaBrasileiro())
    }

    private fun inicializaCampoValor(transacao: Transacao) {
        campoValor.setText(transacao.valor.toString())
    }

}