package com.dishant.kotlinpractices.coreKotlin.coroutines.coroutineScope

import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


// Every and each coroutine has its own quick CoroutineScope
fun main() = runBlocking {

    println("runBlocking : $this")

    launch {
        println("launch : $this")

        launch {
            println("child launch $this")
        }
    }

    async {
        println("async : $this ")
    }

    println("Some other code")
}


//output

//runBlocking : BlockingCoroutine{Active}@5abca1e0
//Some other code
//launch : StandaloneCoroutine{Active}@35d176f7
//async : DeferredCoroutine{Active}@6ebc05a6
//child launch StandaloneCoroutine{Active}@50b494a6
