package com.dishant.kotlinpractices.recyclerViewDemo.viewHolder

import com.dishant.kotlinpractices.recyclerViewDemo.`interface`.Interaction

interface ItemViewHolderBind<T>{
    fun bind(item : T, position: Int, interaction: Interaction<T>)
}

interface ErrorViewHolderBind<T>{
    fun bind(errorMessage:String? , interaction: Interaction<T>)
}

interface PaginationExhaustViewBind<T>{
    fun bind(interaction: Interaction<T>)
}