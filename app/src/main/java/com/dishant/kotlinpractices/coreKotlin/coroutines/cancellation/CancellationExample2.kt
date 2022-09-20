package com.dishant.kotlinpractices.coreKotlin.coroutines.cancellation

import kotlinx.coroutines.*

fun main() = runBlocking {

    println("Main program starts : ${Thread.currentThread().name}")

    val job = launch {
            for(i in 0..500){
                print("$i.")
                delay(50)
            }
    }

    delay(200)
    job.cancelAndJoin()
    job.join()

    println("Main program ends :${Thread.currentThread().name}")
}

//output


//Main program starts : main
//0.1.2.3.Main program ends :main