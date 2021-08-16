package com.dishant.kotlinpractices.coreKotlin.coroutines

import kotlinx.coroutines.*

fun main() {
    GlobalScope.launch {
        delay(2000L)
        println("hello coroutines")
    }

    println("normal part line")
//    Thread.sleep(4000L)

    runBlocking {
        delay(2000L)
    }

//    main2()
    main3()
}


fun main2() = runBlocking {

    launch {
        delay(5000L)
        println("hello scope")
    }

}

fun main3() = runBlocking {



    val job : Job = GlobalScope.launch {
        println("world")
    }

    val job2 : Job = GlobalScope.launch {
        println("hello")
    }

    job.join()
//    job.join()
}