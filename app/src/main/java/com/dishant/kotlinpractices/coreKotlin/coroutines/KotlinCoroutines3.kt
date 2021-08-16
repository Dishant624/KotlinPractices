package com.dishant.kotlinpractices.coreKotlin.coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

fun main(){

    println("Main program starts ${Thread.currentThread().name}")


    GlobalScope.launch {
        println("fake work start ${Thread.currentThread().name}")
        Thread.sleep(1000) // whole thread block for 1s
        println("fake work end ${Thread.currentThread().name}")
    }


    // block the current main thread & wait for coroutine to finish
    // (parctically not right way to wait)
    Thread.sleep(2000)
    println("Main program Ends ${Thread.currentThread().name}")
}

//note : manually add sleep in main thread to complete coroutine

//output

/*Main program starts main
fake work start DefaultDispatcher-worker-2
fake work end DefaultDispatcher-worker-2
Main program Ends main

Process finished with exit code 0*/
