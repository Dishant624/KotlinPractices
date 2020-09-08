package com.example.kotlinpractices.coreKotlin.ControlFlow

fun main() {
    for ( number in 1..10){
        println(number)
    }

    for(alphabets in 'a'..'z' step 2){
        print(alphabets)
    }

    //reverse order
    for(number  in 3 downTo 0){
        println(number)
    }


//    check values in ranges
    val x =10
    if(x in 10..20){
        println("$x in range in 10 to 20")
    }
}