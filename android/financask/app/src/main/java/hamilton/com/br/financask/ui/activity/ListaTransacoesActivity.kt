package hamilton.com.br.financask.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.AdapterView
import hamilton.com.br.financask.R
import hamilton.com.br.financask.TransacaoDAO
import hamilton.com.br.financask.model.Tipo
import hamilton.com.br.financask.model.Transacao
import hamilton.com.br.financask.ui.ResumoView
import hamilton.com.br.financask.ui.adapter.ListaTransacoesAdapter
import hamilton.com.br.financask.ui.dialog.AdicionaTransacaoDialog
import hamilton.com.br.financask.ui.dialog.AlteraTransacaoDialog
import kotlinx.android.synthetic.main.activity_lista_transacoes.*

class ListaTransacoesActivity : AppCompatActivity() {

    private val dao = TransacaoDAO()
    private val transacoes = dao.transacoes

    private val viewDaActivity by lazy {
        window.decorView
    }

    private val viewGroupDaActivity by lazy {
        viewDaActivity as ViewGroup
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        configuraResumo()
        configuraLista()
        configuraFab()
    }

    private fun configuraFab() {
        lista_transacoes_adiciona_receita.setOnClickListener {
            chamaDialogDeAdicao(Tipo.RECEITA)
        }

        lista_transacoes_adiciona_despesa.setOnClickListener {
            chamaDialogDeAdicao(Tipo.DESPESA)
        }
    }

    private fun chamaDialogDeAdicao(tipo: Tipo) {
        AdicionaTransacaoDialog(viewGroupDaActivity, this)
                .chama(tipo) { transacaoCriada ->
                    adiciona(transacaoCriada)
                    lista_transacoes_adiciona_menu.close(true)
                }
    }

    private fun atualizarTransacoes() {
        configuraLista()
        configuraResumo()
    }

    private fun configuraResumo() {
        val resumoView = ResumoView(this, viewDaActivity, transacoes)
        resumoView.atualiza()
    }

    private fun configuraLista() {
        val listaTransacoesAdapter = ListaTransacoesAdapter(transacoes, this)
        with(lista_transacoes_listview) {
            adapter = listaTransacoesAdapter
            setOnItemClickListener { _, _, position, _ ->
                val transacao = transacoes[position]
                chamaDialogDeAlteracao(transacao, position)
            }

            setOnCreateContextMenuListener { menu, _, _ ->
                menu.add(Menu.NONE, 1, Menu.NONE, "Remover")
            }
        }
    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {
        val itemId = item?.itemId
        if(itemId==1){
            val adapterMenuInfo = item?.menuInfo as AdapterView.AdapterContextMenuInfo
            val posicao = adapterMenuInfo.position
            remover(posicao)
        }

        return super.onContextItemSelected(item)
    }

    private fun chamaDialogDeAlteracao(transacao: Transacao, position: Int) {
        AlteraTransacaoDialog(viewGroupDaActivity, this)
                .chama(transacao) { transacaoAlterada ->
                    altera(position, transacaoAlterada)
                }
    }

    private fun adiciona(transacao: Transacao) {
        dao.adiciona(transacao)
        atualizarTransacoes()
    }

    private fun altera(posicao: Int, transacao: Transacao) {
        dao.altera(transacao, posicao)
        atualizarTransacoes()
    }

    private fun remover(posicao: Int) {
        dao.remove(posicao)
        atualizarTransacoes()
    }

}
