package com.dishant.kotlinpractices.recyclerViewDemo.utils

data class Operation<out T>(val data: T, val operationEnum: OperationEnum)

enum class OperationEnum {
    Insert,
    Update,
    Delete
}
