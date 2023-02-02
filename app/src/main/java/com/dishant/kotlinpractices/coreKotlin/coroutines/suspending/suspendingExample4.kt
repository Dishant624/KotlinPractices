package com.dishant.kotlinpractices.coreKotlin.coroutines.suspending

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

// Structured concurrency with async
fun main() = runBlocking{
    val time = measureTimeMillis {
        println("The answer is ${concurrentSum()} ")
    }

    newSingleThreadContext("context1").use {

    }

    println("Main program ends in $time ms")
}

suspend fun concurrentSum() : Int = coroutineScope {
    val  one = async { doSomethingUsefulOne() }
    val  two = async { doSomethingUsefulTwo() }
    one.await() + two.await()
}

suspend fun doSomethingUsefulOne() : Int{
    delay(1000L)
    return 13;
}

suspend fun doSomethingUsefulTwo() : Int{
    delay(1000L)
    return 42;
}
