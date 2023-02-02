package com.dishant.kotlinpractices.recyclerViewDemo.viewHolder

import androidx.recyclerview.widget.RecyclerView
import com.dishant.kotlinpractices.databinding.CellPaginationExhaustBinding
import com.dishant.kotlinpractices.recyclerViewDemo.RecyclerViewModel
import com.dishant.kotlinpractices.recyclerViewDemo.`interface`.Interaction

class PaginationExhaustViewHolder(private val binding : CellPaginationExhaustBinding) : RecyclerView.ViewHolder(binding.root), PaginationExhaustViewBind<RecyclerViewModel> {
    override fun bind(interaction: Interaction<RecyclerViewModel>) {
        binding.topButton.setOnClickListener{interaction.onExhaustButtonPressed()}
    }

}
