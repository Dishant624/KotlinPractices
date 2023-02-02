package com.dishant.kotlinpractices.recyclerViewDemo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dishant.kotlinpractices.databinding.*
import com.dishant.kotlinpractices.recyclerViewDemo.RecyclerViewModel
import com.dishant.kotlinpractices.recyclerViewDemo.`interface`.Interaction
import com.dishant.kotlinpractices.recyclerViewDemo.`interface`.RecyclerViewInteraction
import com.dishant.kotlinpractices.recyclerViewDemo.utils.RecyclerViewDiffUtilCallBack
import com.dishant.kotlinpractices.recyclerViewDemo.utils.RecyclerViewEnum
import com.dishant.kotlinpractices.recyclerViewDemo.viewHolder.*

class RecyclerViewAdapter(
    override val interaction: Interaction<RecyclerViewModel>,
    private val extraInteraction: RecyclerViewInteraction<RecyclerViewModel>
) : BaseAdapter<RecyclerViewModel>(interaction) {
    override fun handleDiffUtil(newList: ArrayList<RecyclerViewModel>) {
        val diffUtil = RecyclerViewDiffUtilCallBack(arrayList, newList)

        val diffResult = DiffUtil.calculateDiff(diffUtil, true)

        arrayList = newList.toList() as ArrayList<RecyclerViewModel>

        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            RecyclerViewEnum.Loading.value -> LoadingViewHolder(CellLoadingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,false
            ))

            RecyclerViewEnum.View.value -> ItemViewHolder(
                CellItemBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    ), parent, false
                ), extraInteraction
            )

            RecyclerViewEnum.PaginationLoading.value -> PaginationLoadingViewHolder(
                CellPaginationloadingBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    ),parent,false
                )
            )

            RecyclerViewEnum.PaginationExhaust.value -> PaginationExhaustViewHolder(
                CellPaginationExhaustBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    ),parent,false
                )
            )

            RecyclerViewEnum.Error.value -> ErrorItemViewHolder(
                CellErrorBinding.inflate(
                    LayoutInflater.from(parent.context),parent,false
                )
            )

            else -> EmptyViewHolder(
                CellEmptyBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }

    }
}