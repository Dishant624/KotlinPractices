package com.dishant.kotlinpractices.recyclerViewDemo.utils

sealed class NetworkResponse<out T> {

    data class Loading (val isPaginating : Boolean = false) : NetworkResponse<Nothing>()

    data class Success<out T> ( val data : T, val isPaginating: Boolean = false) : NetworkResponse<T>()

    data class Failure(val errorMessage : String ,val isPaginatingError: Boolean = false) : NetworkResponse<Nothing>()
}