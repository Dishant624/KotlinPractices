package com.dishant.kotlinpractices.coreKotlin.coroutines.timeouts

import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout


// handle timeout exception

fun main() = runBlocking {

    println("Main program starts ${Thread.currentThread().name}")

    withTimeout(2000){
        try {
            for(i in 0..300){
                print("$i.")
                delay(100)
            }
        } catch (e: TimeoutCancellationException) {
            println("time out for coroutine :${e.message}")
        } finally {
            println("close resources")
        }
    }

    println("Main program ends ${Thread.currentThread().name}")
}


// output

//Main program starts main
//0.1.2.3.4.5.6.7.8.9.10.11.12.13.14.15.16.17.18.19.Exception in thread "main" kotlinx.coroutines.TimeoutCancellationException: Timed out waiting for 2000 ms
//at kotlinx.coroutines.TimeoutKt.TimeoutCancellationException(Timeout.kt:184)
//at kotlinx.coroutines.TimeoutCoroutine.run(Timeout.kt:154)
//at kotlinx.coroutines.EventLoopImplBase$DelayedRunnableTask.run(EventLoop.common.kt:502)
//at kotlinx.coroutines.EventLoopImplBase.processNextEvent(EventLoop.common.kt:279)
//at kotlinx.coroutines.DefaultExecutor.run(DefaultExecutor.kt:108)
//at java.base/java.lang.Thread.run(Thread.java:829)
