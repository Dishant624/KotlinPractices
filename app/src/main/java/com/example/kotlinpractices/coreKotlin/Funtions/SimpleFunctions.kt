package com.example.kotlinpractices.coreKotlin.Funtions

fun main(args: Array<String>) {
    printMessage("Hello")

    printMessageWithPrefix("Hello","Dishant")

    printMessageWithPrefix("Hello")

    //Calls the same function using named arguments and changing the order of the arguments.
    printMessageWithPrefix(prefix = "Hello" ,message = "Dishant")

    println(sum(10,50))

    println(multiply(10,7))
}

fun printMessage(message :String){
    println(message)
}

fun printMessageWithPrefix(message: String, prefix:String="Info"){
    println("[$prefix] $message")
}

fun sum(x:Int, y:Int): Int {
    return x+y;
}


//A single-expression function that returns an integer (inferred).
fun multiply(x:Int, y:Int) = x*y