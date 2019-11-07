package hamilton.com.br.financask.ui.dialog

import android.content.Context
import android.view.ViewGroup
import hamilton.com.br.financask.R
import hamilton.com.br.financask.model.Tipo


class AdicionaTransacaoDialog(view: ViewGroup,
                              context: Context) : FormularioTransacaoDialog(context, view) {
    override val tituloBotaoPositivo: String
        get() = "Adicionar"

    override fun tituloPor(tipo: Tipo): Int {
        if(tipo == Tipo.RECEITA){
            return R.string.adiciona_receita
        }
        return R.string.adiciona_despesa
    }



}