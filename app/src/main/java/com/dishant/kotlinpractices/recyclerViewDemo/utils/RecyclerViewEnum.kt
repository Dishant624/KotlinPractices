package com.dishant.kotlinpractices.recyclerViewDemo.utils

enum class RecyclerViewEnum(val value: Int) {
    Empty(0),
    Loading(1),
    Error(2),
    View(3),
    PaginationLoading(4),
    PaginationError(5),
    PaginationExhaust(6),
}