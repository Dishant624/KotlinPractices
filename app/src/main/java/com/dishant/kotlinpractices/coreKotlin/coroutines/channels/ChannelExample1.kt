package com.dishant.kotlinpractices.coreKotlin.coroutines.channels

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


//Channels are communication primitives that allow passing data between different coroutine.
// one coroutine can send some information to channel, while other can receive this information from it.
fun main()= runBlocking {

    val channel = Channel<String>()

    //producer coroutine
    launch {
        channel.send("A1")
        channel.send("A2")
        println("A done")
    }

    //producer coroutine
    launch {
        channel.send("B1")
        channel.send("B2")
        println("B done")
    }

    launch {
        repeat(4){
            val valueReceived = channel.receive()
            println(valueReceived)
        }
    }

    println("Main program ends")
}