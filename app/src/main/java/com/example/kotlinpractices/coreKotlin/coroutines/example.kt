package com.example.kotlinpractices.coreKotlin.coroutines

import com.example.kotlinpractices.coreKotlin.SpacialClasses.User
import kotlinx.coroutines.*


fun main() = runBlocking{

    //using Dispatcher.Main give error
    launch(Dispatchers.Default){
        fetchUserAndShow()
    }

    //above launch is same below both are same
//    launch {
//        fetchUserAndShow()
//    }

    println()


}

suspend fun fetchUser () :User{

    return GlobalScope.async {
        delay(2000)
        return@async User("Dishant",1)
    }.await()
}

suspend fun fetchUserAndShow(){
    val user = fetchUser()
    with(user){
        print("name :$name id :$id")
    }
}