package com.example.kotlinpractices.coreKotlin.scopeFuction

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


data class User(var name:String,var lastname : String, var age:Int)
fun main() {


    fun userCreated(user: User){
        println("new user ${user.name} created ")
    }

    //also return referenced object
    val dishant : User = User(
        "Dishant",
        "patel",
        26
    )
        .also {
            userCreated(it)
            true
        }

    print(dishant)

    GlobalScope.launch {
        delay(1000L)
        print("hello coroutine")
    }

    runBlocking {     // but this expression blocks the main thread
        delay(2000L)  // ... while we delay for 2 seconds to keep JVM alive
    }

}