package com.dishant.kotlinpractices.coreKotlin.coroutines

import kotlinx.coroutines.*

// use main method function as expression
fun main() = runBlocking { //create new coroutine that block the current main thread
    println("Main program starts ${Thread.currentThread().name}") // main thread


    //launch acquire runBlocking scope/thread
    val job: Job = launch {// launch a new coroutine in the scope of runBlocking
        println("fake work start ${Thread.currentThread().name}") // thread main
        delay(1000) // coroutine is suspended(paused) but thread main is free( not block)
        println("fake working ... ${Thread.currentThread().name}")// thread main
        delay(1000)
        println("fake working... ${Thread.currentThread().name}")// thread main
        delay(1000)
        println("fake work end ${Thread.currentThread().name}")// thread main
    }


    // delay(4000) //main thread : wait for coroutine to finish
    job.join()
    println("Main program Ends ${Thread.currentThread().name}") //main thread
}



//note : delay is suspend function and used to pause the coroutine for some time
// suspend function is not called from outside a coroutine
//launch function....
// launch a new coroutine without blocking the current thread
// -- >inherits the thread & coroutine scope of immediate parent coroutine
// -- >returns a reference to the Job object
// -- >using Job object we can cancel the coroutine or wait for finish coroutine
// runBlocking  is create  blocking coroutine (means it block main thread)

//output

/*Main program starts main
fake work start main
fake working ... main
fake working... main
fake work end main
Main program Ends main

Process finished with exit code 0*/
