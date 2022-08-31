package com.dishant.kotlinpractices.KotlinJetbrainPrcatice.day1

import java.io.File

fun main(){


    var nullValue : String? = "data"

    val value =nullValue?.let {
        println(it)
    }

    print(value)
    val numbers = File("/Users/admin/LivebirdProjects/PracticeProjets/KotlinPractices/app/src/main/java/com/dishant/kotlinpractices/KotlinJetbrainPrcatice/day1/input.txt")
        .readLines()
        .map(String :: toInt)

    val complements = numbers.associateBy { 2020 - it }


    println(numbers.take(5).associate { "key$it" to "value$it"  })
    println(numbers.take(5).associateBy { "key$it"})
    println(numbers.take(5).associateWith { "value$it"})


}