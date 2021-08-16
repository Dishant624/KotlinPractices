package com.dishant.kotlinpractices.coreKotlin.extra

fun main() {
    val collection = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    println(collection.take(5) + collection.drop(5))

    println(collection.none { it > 5 })

    println(collection.all { it < 11 })

    println(collection.sorted().reversed())
}
