package com.dishant.kotlinpractices.recyclerViewDemo.`interface`

import android.icu.text.Transliterator.Position

interface Interaction<T> {
    fun onItemSelected(item: T, position : Int)
    fun onLongPressed(item: T)
    fun onErrorRefreshPressed()
    fun onExhaustButtonPressed()
}
