
package br.com.alura.technews.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.technews.R
import br.com.alura.technews.database.AppDatabase
import br.com.alura.technews.databinding.ActivityVisualizaNoticiaBinding
import br.com.alura.technews.model.Noticia
import br.com.alura.technews.repository.NoticiaRepository
import br.com.alura.technews.ui.activity.extensions.mostraErro

private const val NOTICIA_NAO_ENCONTRADA = "Notícia não encontrada"
private const val TITULO_APPBAR = "Notícia"
private const val MENSAGEM_FALHA_REMOCAO = "Não foi possível remover notícia"

class VisualizaNoticiaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVisualizaNoticiaBinding

    private val noticiaId: Long by lazy {
        intent.getLongExtra(NOTICIA_ID_CHAVE, 0)
    }
    private val repository by lazy {
        NoticiaRepository(AppDatabase.getInstance(this).noticiaDAO)
    }
    private lateinit var noticia: Noticia

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVisualizaNoticiaBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        title = TITULO_APPBAR
        verificaIdDaNoticia()
    }

    override fun onResume() {
        super.onResume()
        buscaNoticiaSelecionada()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.visualiza_noticia_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            R.id.visualiza_noticia_menu_edita -> abreFormularioEdicao()
            R.id.visualiza_noticia_menu_remove -> remove()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun buscaNoticiaSelecionada() {
        repository.buscaPorId(noticiaId, quandoSucesso = { noticiaEncontrada ->
            noticiaEncontrada?.let {
                this.noticia = it
                preencheCampos(it)
            }
        })
    }

    private fun verificaIdDaNoticia() {
        if (noticiaId == 0L) {
            mostraErro(NOTICIA_NAO_ENCONTRADA)
            finish()
        }
    }

    private fun preencheCampos(noticia: Noticia) {
        binding.activityVisualizaNoticiaTitulo.text = noticia.titulo
        binding.activityVisualizaNoticiaTexto.text = noticia.texto
    }

    private fun remove() {
        if (::noticia.isInitialized) {
            repository.remove(noticia, quandoSucesso = {
                finish()
            }, quandoFalha = {
                mostraErro(MENSAGEM_FALHA_REMOCAO)
            })
        }
    }

    private fun abreFormularioEdicao() {
        val intent = Intent(this, FormularioNoticiaActivity::class.java)
        intent.putExtra(NOTICIA_ID_CHAVE, noticiaId)
        startActivity(intent)
    }

}
