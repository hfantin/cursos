package com.example.ceep.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.ceep.R
import com.example.ceep.databinding.ActivityFormularioNotaBinding
import com.example.ceep.model.Nota

class FormularioNotaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFormularioNotaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormularioNotaBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_formulario_nota_salva, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (ehMenuSalvaNota(item)) {
            val notaCriada = criarNota()
            retornaNota(notaCriada)
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun retornaNota(notaCriada: Nota) {
        setResult(RESULT_OK, Intent().apply {
            putExtra(NOTA, notaCriada)
        })
    }

    private fun criarNota() = Nota(binding.formularioNotaTitulo.text.toString(), binding.formularioNotaDescricao.text.toString())

    private fun ehMenuSalvaNota(item: MenuItem) = item.itemId === R.id.menu_formulario_nota_ic_salva
}