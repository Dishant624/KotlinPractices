package com.dishant.kotlinpractices.coreKotlin.scopeFuction

fun main() {

    val isEmpty : Boolean = "Dishant".let {
        customPrint(it)
        it.isEmpty()
    }

//     let and run work same
    // diff is that we use it for referance of the class to call its methods
    //in run block we direct call is method without using (this)
    val isEmpty2 : Boolean = "Dishant".run {
        customPrint(this)
        isEmpty()
    }

    val toLowercase  by lazy { "Dishant".let {
        customPrint(it)
        it.toLowerCase()
    }}

    println(" is empty $isEmpty")
//    println(" is empty $isEmpty2")
//    println(" in lowercase $toLowercase")

    fun printNonNull(str:String?){
        println("Printing $str :")

        str?.let {
            customPrint(str)
        }
    }
//
//    printNonNull(null)
//    printNonNull("This is not null")
}
// this function is call when variable is initialized but change when using lazy
fun customPrint(string: String) {
    println(string.toUpperCase())
}
