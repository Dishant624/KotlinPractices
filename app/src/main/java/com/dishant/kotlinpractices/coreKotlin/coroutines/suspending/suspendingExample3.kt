package com.dishant.kotlinpractices.coreKotlin.coroutines.suspending

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

// coroutine execute lazy way
fun main() = runBlocking {
    println("Main program starts ${Thread.currentThread().name}")

        val msgOne = async (start = CoroutineStart.LAZY){ getMessageOne()} // this coroutine executed when is output is await to execute otherwise not executed
        val msgTwo = async (start = CoroutineStart.LAZY){ getMessageTwo()} // this coroutine executed when is output is await to execute otherwise not executed
//        println("The entire message is ${msgOne.await() + msgTwo.await()}")


    println("Main program ends ${Thread.currentThread().name}")
}

//out
//Main program starts main
//The entire message is Helloworld
//Completed in 1012 ms
//Main program ends main

