package com.dishant.kotlinpractices.coreKotlin.kotlinFlow

import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking


fun main() = runBlocking<Unit> {
    simpleFlow()
        .filter { it % 2== 0 }
        .map {
            "The Current number is $it"
        }.collectLatest {
            println(it)
        }
}


//output
//The Current number is 1
//The Current number is 2
//The Current number is 3
