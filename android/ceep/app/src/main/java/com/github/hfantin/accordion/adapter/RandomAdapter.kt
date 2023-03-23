package com.example.ceep.ui.recyclerview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.ceep.databinding.RowContentBinding
import com.example.ceep.databinding.RowTitleBinding
import com.github.hfantin.accordion.component.AccordionAdapter
import com.github.hfantin.accordion.component.AccordionView
import com.github.hfantin.accordion.model.DataModel

class RandomAdapter(val dataArray: List<DataModel>) : AccordionAdapter {
    override fun onCreateViewHolderForTitle(parent: ViewGroup): AccordionView.ViewHolder {
        val binding: RowTitleBinding = RowTitleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TitleViewHolder(binding)
    }

    override fun onCreateViewHolderForContent(parent: ViewGroup): AccordionView.ViewHolder {
        val binding: RowContentBinding = RowContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContentViewHolder(binding)
    }

    override fun onBindViewForTitle(viewHolder: AccordionView.ViewHolder, position: Int, arrowDirection: AccordionAdapter.ArrowDirection) {
        val dataModel = dataArray[position]
        (viewHolder as TitleViewHolder).bind(dataModel, arrowDirection)
    }

    override fun onBindViewForContent(viewHolder: AccordionView.ViewHolder, position: Int) {
        val dataModel = dataArray[position]
        (viewHolder as ContentViewHolder).bind(dataModel)
    }

    override fun getItemCount() = dataArray.size
}

class TitleViewHolder(private val binding: RowTitleBinding) : AccordionView.ViewHolder(binding.root) {
    fun bind(dataModel: DataModel, arrowDirection: AccordionAdapter.ArrowDirection) {
        binding.titleTextView.text = dataModel.title
        binding.titleArrowIcon.text = when (arrowDirection) {
            AccordionAdapter.ArrowDirection.UP -> "▲"
            AccordionAdapter.ArrowDirection.DOWN -> "▼"
            else -> ""
        }
    }
}

class ContentViewHolder(private val binding: RowContentBinding) : AccordionView.ViewHolder(binding.root) {
    fun bind(dataModel: DataModel) {
        binding.contentTextView.text = dataModel.desc
    }
}