package com.example.kotlinpractices.coreKotlin.coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

fun main(){

    println("Main program starts ${Thread.currentThread().name}")


    GlobalScope.launch {
        println("fake work start ${Thread.currentThread().name}")
        Thread.sleep(1000)
        println("fake work end ${Thread.currentThread().name}")
    }


    println("Main program Ends ${Thread.currentThread().name}")
}

//note : Unlike thread , for coroutines application by default does not
// wait for it to finish its execution

//output

/*Main program starts main
Main program Ends main

Process finished with exit code 0*/
