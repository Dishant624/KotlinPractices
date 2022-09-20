package com.dishant.kotlinpractices.coreKotlin.coroutines.suspending

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

// coroutine execute concurrent way
fun main() = runBlocking {
    println("Main program starts ${Thread.currentThread().name}")

    val timeTakenToExecute = measureTimeMillis {
        val msgOne = async{ getMessageOne()} // async is coroutine builder and create new coroutine in runblocking scope
        val msgTwo = async { getMessageTwo()} // async is coroutine builder and create new coroutine in runblocking scope
        println("The entire message is ${msgOne.await() + msgTwo.await()}")
    }

    println("Completed in $timeTakenToExecute ms")
    println("Main program ends ${Thread.currentThread().name}")
}

//out
//Main program starts main
//The entire message is Helloworld
//Completed in 1012 ms
//Main program ends main

