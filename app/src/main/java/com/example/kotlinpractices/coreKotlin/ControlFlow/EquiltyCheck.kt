package com.example.kotlinpractices.coreKotlin.ControlFlow

fun main() {

    val writers = setOf("one", "two" , "three")
    val authors = setOf("two","one","three")

    //structural values compare
    if(writers==authors){
        println("all are equal values")
    }

    if(writers===authors){
        println("all are same reference")
    }else{
        println("all are not same reference")
    }
}