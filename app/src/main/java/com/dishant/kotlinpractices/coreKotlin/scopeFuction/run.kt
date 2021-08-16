package com.dishant.kotlinpractices.coreKotlin.scopeFuction

fun main() {


    fun getNullableLength(str :String?): Int? {
        println("for $str :")

        return str?.run {
                println("is empty ${isEmpty()}")
                length
        }

    }
//    println(getNullableLength(null))
//    println(getNullableLength(""))
//    println(getNullableLength("Dishant"))

    println("--------------------------")
    getNullableLength(null)?.run { println("lenght :$this") }
    getNullableLength(null)?.let { println("lenght :$it") }
    getNullableLength("")?.run { println("lenght :$this") }
    getNullableLength("Prashant")?.run { println("lenght :$this") }


}