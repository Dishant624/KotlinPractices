package com.dishant.kotlinpractices.coreKotlin.coroutines.cancellation

import kotlinx.coroutines.*

fun main() = runBlocking {

    println("Main program starts : ${Thread.currentThread().name}")

    //check coroutine periodically that it has been cancel or not by using isActive flag in coroutine
    val job = launch(Dispatchers.Default) {
        for(i in 0..500){
            if(!isActive)
                break
            print("$i.")
            //add delay because it execute quickly and cancel
            Thread.sleep(2)
        }
    }

    delay(10)
    job.cancelAndJoin()

    println("Main program ends :${Thread.currentThread().name}")
}

//output


//Main program starts : main
//0.1.2.3.4.5.6.Main program ends :main