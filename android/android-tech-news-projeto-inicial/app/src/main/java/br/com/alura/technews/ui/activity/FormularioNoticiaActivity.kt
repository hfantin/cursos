package br.com.alura.technews.ui.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import br.com.alura.technews.R
import br.com.alura.technews.database.AppDatabase
import br.com.alura.technews.databinding.ActivityFormularioNoticiaBinding
import br.com.alura.technews.model.Noticia
import br.com.alura.technews.repository.NoticiaRepository
import br.com.alura.technews.ui.activity.extensions.mostraErro
import br.com.alura.technews.ui.viewmodel.FormularioNoticiaViewModel
import br.com.alura.technews.ui.viewmodel.FormularioNoticiaViewModelFactory
import br.com.alura.technews.ui.viewmodel.ListaNoticiasViewModel
import br.com.alura.technews.ui.viewmodel.ListaNoticiasViewModelFactory

private const val TITULO_APPBAR_EDICAO = "Editando notícia"
private const val TITULO_APPBAR_CRIACAO = "Criando notícia"
private const val MENSAGEM_ERRO_SALVAR = "Não foi possível salvar notícia"

class FormularioNoticiaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFormularioNoticiaBinding

    private val noticiaId: Long by lazy {
        intent.getLongExtra(NOTICIA_ID_CHAVE, 0)
    }

    private val viewModel: FormularioNoticiaViewModel by viewModels(factoryProducer = {
        FormularioNoticiaViewModelFactory(NoticiaRepository(AppDatabase.getInstance(this).noticiaDAO))
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormularioNoticiaBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        definindoTitulo()
        preencheFormulario()
    }

    private fun definindoTitulo() {
        title = if (noticiaId > 0) {
            TITULO_APPBAR_EDICAO
        } else {
            TITULO_APPBAR_CRIACAO
        }
    }

    private fun preencheFormulario() {
        viewModel.buscaPorId(noticiaId).observe(this) { noticiaEncontrada ->
            if (noticiaEncontrada != null) {
                binding.activityFormularioNoticiaTitulo.setText(noticiaEncontrada.titulo)
                binding.activityFormularioNoticiaTexto.setText(noticiaEncontrada.texto)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.formulario_noticia_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.formulario_noticia_salva -> {
                val titulo = binding.activityFormularioNoticiaTitulo.text.toString()
                val texto = binding.activityFormularioNoticiaTexto.text.toString()
                salva(Noticia(noticiaId, titulo, texto))
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun salva(noticia: Noticia) {
        viewModel.salva(noticia).observe(this) {
            if (it.erro == null) {
                finish()
            } else {
                mostraErro(MENSAGEM_ERRO_SALVAR)
            }
        }
    }
}
