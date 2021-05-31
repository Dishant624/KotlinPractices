 package com.example.kotlinpractices.coreKotlin.coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.concurrent.thread
import kotlin.system.measureTimeMillis

 fun main(){
     runBlocking {//create new coroutine that block the current main thread
         println("Main program starts ${Thread.currentThread().name}") // main thread


         GlobalScope.launch {// thread t1
             println("fake work start ${Thread.currentThread().name}") // thread t1
             delay(1000) // coroutine is suspended(paused) but thread t1 is free( not block)
             println("fake working ... ${Thread.currentThread().name}")// execute in t1 or some other thread
             delay(1000)
             println("fake working... ${Thread.currentThread().name}")// execute in t1 or some other thread
             delay(1000)
             println("fake work end ${Thread.currentThread().name}")// execute in t1 or some other thread
         }


         //create new coroutine that block the current main thread
         delay(4000) //main thread : wait for coroutine to finish

         println("Main program Ends ${Thread.currentThread().name}") //main thread
     }
}

//note : delay is suspend function and used to pause the coroutine for some time
// suspend function is not called from outside a coroutine
 // GlobalScope.launch is create not blocking coroutine (means it does not block main thread)
 // runBlocking  is create  blocking coroutine (means it block main thread)

//output

/*Main program starts main
fake work start DefaultDispatcher-worker-1
fake working ... DefaultDispatcher-worker-1
fake working... DefaultDispatcher-worker-3
fake work end DefaultDispatcher-worker-2
Main program Ends main

Process finished with exit code 0*/
