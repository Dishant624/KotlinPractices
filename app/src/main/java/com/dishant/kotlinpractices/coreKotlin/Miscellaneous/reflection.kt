package com.dishant.kotlinpractices.coreKotlin.Miscellaneous

class Reflection {
}

fun main(){

    val abc = Reflection::class
    println("This is a class reference $abc")

    val obj = Reflection()
    println("This is a bounded class reference ${obj::class}")
    println("This is a bounded class reference $obj")

}