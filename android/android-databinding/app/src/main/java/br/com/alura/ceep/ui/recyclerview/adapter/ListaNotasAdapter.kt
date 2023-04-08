package br.com.alura.ceep.ui.recyclerview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.ceep.databinding.ItemNotaBinding
import br.com.alura.ceep.model.Nota

class ListaNotasAdapter(
    var onItemClickListener: (notaSelecionada: Nota) -> Unit = {}
) : ListAdapter<Nota, ListaNotasAdapter.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val viewDataBinding = ItemNotaBinding.inflate(inflater, parent, false)
        return ViewHolder(viewDataBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { nota ->
            holder.vincula(nota)
        }
    }

    inner class ViewHolder(private val viewDataBinding: ItemNotaBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root), View.OnClickListener {

        private lateinit var nota: Nota

        init {
            viewDataBinding.clicaNaNota = this
        }

        override fun onClick(v: View?) {
            if (::nota.isInitialized) {
                onItemClickListener(nota)
            }
        }

        fun vincula(nota: Nota) {
            this.nota = nota
            viewDataBinding.nota = nota
        }

    }

}

object DiffCallback : DiffUtil.ItemCallback<Nota>() {
    override fun areItemsTheSame(
        oldItem: Nota,
        newItem: Nota
    ) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Nota, newItem: Nota) = oldItem == newItem

}