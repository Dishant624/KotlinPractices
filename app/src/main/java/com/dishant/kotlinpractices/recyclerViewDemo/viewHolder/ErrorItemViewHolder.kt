package com.dishant.kotlinpractices.recyclerViewDemo.viewHolder

import androidx.recyclerview.widget.RecyclerView
import com.dishant.kotlinpractices.databinding.CellErrorBinding
import com.dishant.kotlinpractices.recyclerViewDemo.RecyclerViewModel
import com.dishant.kotlinpractices.recyclerViewDemo.`interface`.Interaction

class ErrorItemViewHolder(private val binding: CellErrorBinding) :
    RecyclerView.ViewHolder(binding.root), ErrorViewHolderBind<RecyclerViewModel> {
    override fun bind(errorMessage: String?, interaction: Interaction<RecyclerViewModel>) {
        binding.errorTxt.text = errorMessage
        binding.refreshBtn.setOnClickListener {
            interaction.onErrorRefreshPressed()
        }
    }
}