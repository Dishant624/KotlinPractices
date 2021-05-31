package com.example.kotlinpractices.coreKotlin.coroutines

import kotlin.concurrent.thread

fun main(){

    println("Main program starts ${Thread.currentThread().name}")


    thread {
        println("fake work start ${Thread.currentThread().name}")
        Thread.sleep(1000)
        println("fake work end ${Thread.currentThread().name}")
    }


    println("Main program Ends ${Thread.currentThread().name}")
}

//note : when using thread application wait to finish other background thread
// execution

//output

/* Main program starts main
Main program Ends main
fake work start Thread-0
fake work end Thread-0

Process finished with exit code 0*/
