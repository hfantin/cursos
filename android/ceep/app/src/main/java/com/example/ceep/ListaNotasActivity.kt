package com.example.ceep

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.example.ceep.dao.NotaDAO
import com.example.ceep.model.Nota
import com.example.ceep.ui.adapter.ListaNotasAdapter

class ListaNotasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_notas)

        val listaNotas = findViewById<ListView>(R.id.listView)

        val dao = NotaDAO()
        dao.insere(
            Nota("Primeira nota",
            "Primeira descrição")
        );
       val  todasNotas = dao.todos();

        listaNotas.setAdapter(ListaNotasAdapter(this, todasNotas))


    }
}