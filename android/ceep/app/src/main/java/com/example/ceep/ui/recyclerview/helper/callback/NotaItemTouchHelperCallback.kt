package com.example.ceep.ui.recyclerview.helper.callback

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.ceep.dao.NotaDAO
import com.example.ceep.ui.recyclerview.adapter.ListaNotasAdapter


class NotaItemTouchHelperCallback(val adapter: ListaNotasAdapter) : ItemTouchHelper.Callback() {
    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
        val marcacoesDeDeslize = ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
        return makeMovementFlags(0, marcacoesDeDeslize)
    }

    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val posicaoDaNotaDeslizada = viewHolder.adapterPosition
        NotaDAO().remove(posicaoDaNotaDeslizada)
        adapter.remove(posicaoDaNotaDeslizada)
    }

}
