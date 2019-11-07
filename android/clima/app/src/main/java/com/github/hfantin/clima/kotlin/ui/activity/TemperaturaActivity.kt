package com.github.hfantin.clima.kotlin.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import bb.com.br.clima.R
import com.github.hfantin.clima.java.domain.model.Temperatura
import com.github.hfantin.clima.kotlin.extensions.formatar
import com.github.hfantin.clima.kotlin.ui.adapter.TemperaturaAdapter
import kotlinx.android.synthetic.main.activity_clima.*
import java.util.*

class TemperaturaActivity : AppCompatActivity(){

    private val data = Calendar.getInstance()

    companion object {
        private val TAG = TemperaturaActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreate()")
        setContentView(R.layout.activity_clima)
        title = "Diadema - SP"
        defineListener()
        definirAdapter()
    }

    private fun defineListener() {
        botao.setOnClickListener {
            Toast.makeText(application, "Clicou no botão OK", Toast.LENGTH_LONG).show()
        }
    }

    private fun definirAdapter() {
        val layoutManager = LinearLayoutManager(this)
        temperaturas.layoutManager = layoutManager
        val adapter = TemperaturaAdapter(listaMockada()){
            exibirTemperaturaClicada(it)
        }
        temperaturas.adapter = adapter
    }

    fun exibirTemperaturaClicada(temperatura: Temperatura) {
        val mensagem = "${temperatura.data.formatar()} - ${temperatura.getDescricao()}, maxima de ${temperatura.temperaturaMaxima.formatar()}, minima de ${temperatura.temperaturaMinima.formatar()}"
        Toast.makeText(applicationContext, mensagem, Toast.LENGTH_LONG).show()
    }

    // dados obtidos em https://openweathermap.org/city/3464739
    private fun listaMockada() = listOf(
        Temperatura(R.drawable.ic_chuva_forte, proximoDia(), "Chuva Forte", 24.1, 18.8),
        Temperatura(R.drawable.ic_chuva_moderada, proximoDia(), "Chuva Moderada", 25.7, 21.8),
        Temperatura(R.drawable.ic_chuva_fraca, proximoDia(), "Chuva Fraca", 28.1, 23.6),
        Temperatura(R.drawable.ic_chuva_forte, proximoDia(), "Chuva Forte", 24.7, 21.8),
        Temperatura(R.drawable.ic_chuva_forte, proximoDia(), "Chuva Forte", 23.8, 20.6),
        Temperatura(R.drawable.ic_tempestade, proximoDia(), "Tempestade", 20.3, 15.4),
        Temperatura(R.drawable.ic_chuva_forte, proximoDia(), "Chuva Forte", 21.7, 17.9),
        Temperatura(R.drawable.ic_nublado, proximoDia(), "Nublado", 23.1, 19.3),
        Temperatura(R.drawable.ic_parcialmente_nublado, proximoDia(), "Parcialmente Nublado", 25.2, 21.1),
        Temperatura(R.drawable.ic_sol, proximoDia(), "Sol", 26.9, 22.2))

    private fun proximoDia() : Date{
        data.add(Calendar.DAY_OF_MONTH, 1)
        return data.time
    }

}
