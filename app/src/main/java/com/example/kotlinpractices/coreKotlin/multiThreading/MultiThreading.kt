package com.example.kotlinpractices.coreKotlin.multiThreading

private const val TAG = "MultiThreading"

fun main() {
    Thread(startThread()).apply { start() }
}

fun startThread() : Runnable{
    return Runnable {
        for (number in 1..10){
            println("startThread $number")
            Thread.sleep(1000)
        }
    }
}
