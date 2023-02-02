package com.dishant.kotlinpractices.recyclerViewDemo.utils

import androidx.recyclerview.widget.DiffUtil
import com.dishant.kotlinpractices.recyclerViewDemo.RecyclerViewModel

class RecyclerViewDiffUtilCallBack(
    private val oldList : List<RecyclerViewModel>,
    private val newList : List<RecyclerViewModel>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

//    areContentsTheSame is Called to check whether two items have the same data.
//
//    areItemsTheSame is called to check whether two objects represent the same item.
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return  oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when{
            oldList[oldItemPosition].id != newList[newItemPosition].id -> false
            oldList[oldItemPosition].content != newList[newItemPosition].content -> false
            oldList[oldItemPosition].isLiked != newList[newItemPosition].isLiked -> false
                else ->true
        }
    }

}