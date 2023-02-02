package com.dishant.kotlinpractices.recyclerViewDemo.utils

import android.util.DisplayMetrics
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.android.awaitFrame

const val DEFAULT_JUMP_THRESHOLD = 50
const val DEFAULT_SPEED_FACTOR = 1f

suspend fun RecyclerView.quickToScrollToTop(
    jumpThreshold : Int  = DEFAULT_JUMP_THRESHOLD,
    speedFactor: Float = DEFAULT_SPEED_FACTOR
){

    val linearLayoutManager = layoutManager as LinearLayoutManager

    val smoothScroller = object : LinearSmoothScroller(context){
        init {
            targetPosition = 0
        }

        override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics?) =
            super.calculateSpeedPerPixel(displayMetrics) / speedFactor

    }

    val jumpBeforeScroll = linearLayoutManager.findFirstVisibleItemPosition() > jumpThreshold
    if(jumpBeforeScroll){
        linearLayoutManager.scrollToPositionWithOffset(jumpThreshold,0)
        awaitFrame()
    }

    linearLayoutManager.startSmoothScroll(smoothScroller)
}
