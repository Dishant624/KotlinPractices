package com.dishant.kotlinpractices.coreKotlin.coroutines.cancellation.handleCancelException

import kotlinx.coroutines.*

//we cannot add suspending function in finally block

fun main() = runBlocking {

    println("Main program starts : ${Thread.currentThread().name}")

    val job = launch {
        try {
            for(i in 0..500){
                println("$i.")
                delay(50)
            }
        } catch (e: CancellationException) {
            println("Cancel coroutine calls")
        } finally {
            // add suspend function
            delay(100) // Generally we don't use suspending function in finally block
            println("close all resources")
        }
    }

    delay(200)
    job.cancelAndJoin()

    println("Main program ends :${Thread.currentThread().name}")
}

//output

//Main program starts : main
//0.
//1.
//2.
//3.
//Cancel coroutine calls
//Main program ends :main