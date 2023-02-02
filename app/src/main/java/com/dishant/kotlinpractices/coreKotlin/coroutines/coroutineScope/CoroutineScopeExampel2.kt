package com.dishant.kotlinpractices.coreKotlin.coroutines.coroutineScope

import kotlinx.coroutines.*


// Every and each coroutine has its own quick CoroutineScope
fun main() = runBlocking {

    doWorld()

    println("Some other code done")
}


suspend fun doWorld() = coroutineScope {
    launch {
        delay(2000L)
        println("World 1")
    }

    launch {
        delay(1000L)
        println("World 2")
    }

    println("Hello")
}




//output

//runBlocking : BlockingCoroutine{Active}@5abca1e0
//Some other code
//launch : StandaloneCoroutine{Active}@35d176f7
//async : DeferredCoroutine{Active}@6ebc05a6
//child launch StandaloneCoroutine{Active}@50b494a6
