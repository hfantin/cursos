package com.github.hfantin.clima.kotlin.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import bb.com.br.clima.R
import com.github.hfantin.clima.java.domain.model.Temperatura
import com.github.hfantin.clima.kotlin.extensions.formatar
import kotlinx.android.synthetic.main.item.view.*

class TemperaturaAdapter(private val mTemperaturas: List<Temperatura>,
                         private val itemClick: (Temperatura) -> Unit) :
        RecyclerView.Adapter<TemperaturaAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(mTemperaturas[position])
    }

    override fun getItemCount() = mTemperaturas.size

    class ViewHolder(itemView: View,
                     private val itemClick: (Temperatura) -> Unit)
        : RecyclerView.ViewHolder(itemView) {
        fun  bindView(item: Temperatura){
            with(itemView){
                icone.setImageResource(item.icone)
                data.text = item.data.formatar()
                descricao.text = item.getDescricao()
                temperaturaMaxima.text = "${item.temperaturaMaxima.formatar()} °"
                temperaturaMinima.text = "${item.temperaturaMinima.formatar()} °"
                setOnClickListener { view -> itemClick(item) }
            }
        }
    }

}
