package com.example.kotlinpractices.coreKotlin.multiThreading

private const val TAG = "MultiThreading"

fun main() {
    Thread(startThread()).apply { start() }
    ExampleThread(10).apply { start() }
    Thread(ExampleRunnable(5)).apply { start() }
}

fun startThread() : Runnable{
    return Runnable {
        for (number in 1..10){
            println("startThread $number")
            Thread.sleep(1000)
        }
    }
}

class ExampleThread(private var seconds : Int) : Thread(){
    override fun run() {
        super.run()
        for (number in 1..seconds){
            println("ExampleThread startThread $number")
            Thread.sleep(1000)
        }
    }
}

class ExampleRunnable(private var seconds: Int) : Runnable{
    override fun run() {
        for (number in 1..seconds){
            println("ExampleRunnable startThread $number")
            Thread.sleep(1000)
        }
    }

}
