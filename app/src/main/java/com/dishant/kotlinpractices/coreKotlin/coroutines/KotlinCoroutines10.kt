package com.dishant.kotlinpractices.coreKotlin.coroutines

import kotlinx.coroutines.*

// use main method function as expression
fun main() = runBlocking { //create new coroutine that block the current main thread
    println("Main program starts ${Thread.currentThread().name}") // main thread


    //async acquire runBlocking scope/thread
    val jobDeferred: Deferred<Int> = async {// launch a new coroutine in the scope of runBlocking
        println("fake work start ${Thread.currentThread().name}") // thread main
        delay(1000) // coroutine is suspended(paused) but thread main is free( not block)
        println("fake work end ${Thread.currentThread().name}")// thread main
        20
    }


    // delay(4000) //main thread : wait for coroutine to finish
//    jobDeferred.join()
    var value= jobDeferred.await()

    println("new values $value") //main thread
    println("Main program Ends ${Thread.currentThread().name}") //main thread
}



//note : delay is suspend function and used to pause the coroutine for some time
// suspend function is not called from outside a coroutine
//async function....
// launch a new coroutine without blocking the current thread
// -- >inherits the thread & coroutine scope of immediate parent coroutine
// -- >returns a reference to the Deferred<T> object
// -- >using Deferred object we can cancel the coroutine or wait for finish coroutine
//        or retrieve the returned result
// runBlocking  is create  blocking coroutine (means it block main thread)

//output

/*Main program starts main
fake work start main
fake work end main
new values 20
Main program Ends main

Process finished with exit code 0*/
