package com.dishant.kotlinpractices.recyclerViewDemo.viewHolder

import android.widget.PopupMenu
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.dishant.kotlinpractices.R
import com.dishant.kotlinpractices.databinding.CellItemBinding
import com.dishant.kotlinpractices.recyclerViewDemo.RecyclerViewModel
import com.dishant.kotlinpractices.recyclerViewDemo.`interface`.Interaction
import com.dishant.kotlinpractices.recyclerViewDemo.`interface`.RecyclerViewInteraction

class ItemViewHolder(
    private val binding: CellItemBinding,
    private val extraInteraction: RecyclerViewInteraction<RecyclerViewModel>
) : RecyclerView.ViewHolder(binding.root),
    ItemViewHolderBind<RecyclerViewModel> {
    override fun bind(
        item: RecyclerViewModel,
        position: Int,
        interaction: Interaction<RecyclerViewModel>
    ) {

        val text = "Position $position ${item.text}"

        binding.idTV.text = item.id
        binding.contentTV.text = item.content.ifBlank { text }
        binding.favButton.setImageDrawable(
            ContextCompat.getDrawable(
                binding.root.context,
                if (item.isLiked) R.drawable.ic_baseline_favorite_24 else R.drawable.ic_baseline_favorite_border_24
            )
        )

        binding.moreButton.setOnClickListener {
            val popupMenu = PopupMenu(binding.root.context, binding.moreButton)
            popupMenu.inflate(R.menu.popup_menu)

            popupMenu.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.delete -> {
                        try {
                            extraInteraction.onDeletePressed(item)
                        } catch (e: Exception) {
                            Toast.makeText(
                                binding.root.context,
                                "Please wait before doing any operation.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        return@setOnMenuItemClickListener true
                    }

                    R.id.update -> {

                        try{
                            extraInteraction.onUpdatePressed(item)
                        }catch (e: Exception){
                            Toast.makeText(binding.root.context,"Please wait before doing any operation",Toast.LENGTH_SHORT).show()
                        }

                        return@setOnMenuItemClickListener true
                    }

                    else -> {
                        return@setOnMenuItemClickListener false
                    }
                }
            }

            popupMenu.show()
        }


        binding.root.setOnClickListener {
            try {
                interaction.onItemSelected(item,position)
            } catch (e: Exception) {
                Toast.makeText(
                    binding.root.context,
                    "Please wait before doing any interaction.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding.favButton.setOnClickListener {
            extraInteraction.onLikePressed(item)
        }
    }
}