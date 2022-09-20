package com.dishant.kotlinpractices.coreKotlin.coroutines.coroutineContext

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


// Dispatcher determine thread of coroutine
fun main() = runBlocking {

    // this is for coroutineScope
    // coroutineContext: CoroutineContext instance

    launch {
        println("coroutine scope $this")
        println("coroutine context ${coroutineContext}")
        println("c1 launch : ${Thread.currentThread().name}")
        delay(100)
        print("c1 after 100 ms ${Thread.currentThread().name}")
    }

    /* With parameter: Dispatchers.Default [similar to GlobalScope.launch { } ]
      - Gets its own context at Global level. Executes in a separate background thread.
      - After delay() or suspending function execution,
          it continues to run either in the same thread or some other thread.  */
    launch(Dispatchers.Default) {
        println("C2: ${Thread.currentThread().name}")   // Thread: T1
        delay(1000)
        println("C2 after delay: ${Thread.currentThread().name}")   // Thread: Either T1 or some other thread
    }

    /*  With parameter: Dispatchers.Unconfined      [UNCONFINED DISPATCHER]
        - Inherits CoroutineContext from the immediate parent coroutine.
        - After delay() or suspending function execution, it continues to run in some other thread.  */
    launch(Dispatchers.Unconfined) {
        println("C3: ${Thread.currentThread().name}")   // Thread: main
        delay(1000)
        println("C3 after delay: ${Thread.currentThread().name}")   // Thread: some other thread T1
    }

    launch(coroutineContext) {
        println("C4: ${Thread.currentThread().name}")       // Thread: main
        delay(1000)
        println("C4 after delay: ${Thread.currentThread().name}")   // Thread: main
    }
    println("Some code")

}