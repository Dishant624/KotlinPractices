package com.dishant.kotlinpractices.recyclerViewDemo.`interface`

import androidx.recyclerview.widget.RecyclerView

interface RecyclerViewInteraction<T> {
    fun onUpdatePressed(item: T)
    fun onDeletePressed(item: T)
    fun onLikePressed(item : T)
}