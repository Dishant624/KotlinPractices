package com.dishant.kotlinpractices.coreKotlin.ControlFlow

import com.dishant.kotlinpractices.coreKotlin.Class.Customer

fun main() {


    checkCases(1)
    checkCases("hello")
    checkCases("Hello")
    checkCases(Customer())
    checkCases(3L)


    println(checkCasesAndReturnResult(1))
    println(checkCasesAndReturnResult("hello all"))
    println(checkCasesAndReturnResult(3.0))
    println(checkCasesAndReturnResult(123))

}


//When as a Statment
fun checkCases(obj : Any){

        when(obj){
            1 -> println("this is interger")
            "Hello" -> println("this is Hello word")
            is Long -> println("this is long")
            !is String -> println("this is not string")
            else -> println("Unknown Type")
        }

}

//When as a Expression
fun checkCasesAndReturnResult(obj:Any) : Any{

    val result = when(obj){
        1-> "this is Interger"
        is String -> "this is String"
        else -> "Unkonwn Type"
    }
    return  result
}