package com.dishant.kotlinpractices.coreKotlin.kotlinFlow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking


//link https://proandroiddev.com/reactive-stream-with-flow-android-f936bfdea66d

fun simpleFlow(): Flow<Int> = flow {
    for(num in 1..10){
        delay(100)
        emit(num)
    }
}

fun main() = runBlocking<Unit>{
    simpleFlow().collectLatest {
        println(it)
    }
}


//output

//1
//2
//3
//4
//5
//6
//7
//8
//9
//10