package com.dishant.kotlinpractices.coreKotlin.coroutines.cancellation

import kotlinx.coroutines.*

fun main() = runBlocking {

    println("Main program starts : ${Thread.currentThread().name}")

    val job = launch {
        for(i in 0..500){
            print("$i.")
//            delay(50)
            yield()
        }
    }

    delay(2)
    job.cancelAndJoin()

    println("Main program ends :${Thread.currentThread().name}")
}

//output


//Main program starts : main
//0.1.2.3.4.5.6.7.8.9.10.11.12.13.14.15.16.17.18.19.20.
// 21.22.23.24.25.26.27.28.29.30.31.32.33.34.35.36.37.38.39.40.Main program ends :main