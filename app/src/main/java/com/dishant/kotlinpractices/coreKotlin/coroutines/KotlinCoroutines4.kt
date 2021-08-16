package com.dishant.kotlinpractices.coreKotlin.coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun main(){

    println("Main program starts ${Thread.currentThread().name}")


    GlobalScope.launch {// thread t1
        println("fake work start ${Thread.currentThread().name}") // thread t1
        delay(1000) // coroutine is suspended(paused) but thread t1 is free( not block)
        println("fake working ... ${Thread.currentThread().name}")// execute in t1 or some other thread
        delay(1000)
        println("fake working... ${Thread.currentThread().name}")// execute in t1 or some other thread
        delay(1000)
        println("fake work end ${Thread.currentThread().name}")// execute in t1 or some other thread
    }


    // block the current main thread & wait for coroutine to finish
    // (parctically not right way to wait)
    Thread.sleep(4000)

    //delay(4000) not call like this compiler error
    println("Main program Ends ${Thread.currentThread().name}")
}

//note : delay is suspend function and used to pause the coroutine for some time
// suspend function is not called from outside a coroutine

//output

/*Main program starts main
fake work start DefaultDispatcher-worker-1
fake working ... DefaultDispatcher-worker-1
fake work end DefaultDispatcher-worker-2
fake work end DefaultDispatcher-worker-1
Main program Ends main

Process finished with exit code 0*/
