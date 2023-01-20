package com.example.ceep.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.ceep.dao.NotaDAO
import com.example.ceep.databinding.ActivityListaNotasBinding
import com.example.ceep.model.Nota
import com.example.ceep.ui.recyclerview.adapter.ListaNotasAdapter


class ListaNotasActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListaNotasBinding
    private lateinit var adapter: ListaNotasAdapter
    private val dao: NotaDAO by lazy { inicializarLista() }
    private var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            obterResultado(result.data)
        }
    }

    private fun inicializarLista() =
        NotaDAO().apply {
            (0..2).forEach { this.insere(Nota("Nota $it", "Descrição $it")) }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaNotasBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        configuraBotao()
        configuraAdapter()
    }

    private fun obterResultado(intent: Intent?) {
        intent?.run {
            if (this.hasExtra(NOTA)) {
                val notaRecebida = this.getParcelableExtra<Nota>(NOTA)!!
                dao.insere(notaRecebida)
                adapter.adiciona(notaRecebida)
            }
        }
    }

    private fun configuraBotao() {
        binding.listaNotasInsereNota.setOnClickListener {
            resultLauncher.launch(Intent(this@ListaNotasActivity, FormularioNotaActivity::class.java))
        }
    }

    private fun configuraAdapter() {
        adapter = ListaNotasAdapter(dao.todos())
        binding.listaNotasRecyclerview.adapter = adapter
    }

}