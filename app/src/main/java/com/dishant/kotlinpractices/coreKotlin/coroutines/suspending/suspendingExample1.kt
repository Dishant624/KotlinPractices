package com.dishant.kotlinpractices.coreKotlin.coroutines.suspending

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

// coroutine execute sequentially by default
fun main() = runBlocking {
    println("Main program starts ${Thread.currentThread().name}")

    val timeTakenToExecute = measureTimeMillis {
        val msgOne = getMessageOne() // this function run in runblocking scope
        val msgTwo = getMessageTwo() // this funtion run in same runblocking scope and wait until first function is completed
        println("The entire message is ${msgOne + msgTwo}")
    }

    println("Completed in $timeTakenToExecute ms")
    println("Main program ends ${Thread.currentThread().name}")
}

//output
//Main program starts main
//The entire message is Helloworld
//Completed in 2012 ms
//Main program ends main

suspend fun getMessageOne(): String {
    delay(1000L)
    println("After working in getMessageOne")
    return "Hello"
}

suspend fun getMessageTwo() :String {
    delay(1000L)
    println("After working in getMessageTwo")
    return "world"
}