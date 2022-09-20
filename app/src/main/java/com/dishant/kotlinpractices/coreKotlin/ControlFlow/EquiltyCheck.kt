package com.dishant.kotlinpractices.coreKotlin.ControlFlow

fun main() {

    val writers = setOf("one", "two" , "three")
    val authors = setOf("two","one","three")

    //structural values compare check internal value of object and compare its value
    if(writers==authors){
        println("all are equal values")
    }

    //check the reference of object
    if(writers===authors){
        println(System.identityHashCode(writers))
        println(System.identityHashCode(authors))
        println("all are same reference")
    }else{
        println(System.identityHashCode(writers))
        println(System.identityHashCode(authors))
        println("all are not same reference")
    }

    val writerReference = writers

    if(writers === writerReference){
        println(System.identityHashCode(writers))
        println(System.identityHashCode(writerReference))
        println("all are same reference")
    }else{
        println("all are not same reference")
    }

    if(writers == writerReference){
        println("all are equal value")
    }else{
        println("all are not equal value")
    }


}