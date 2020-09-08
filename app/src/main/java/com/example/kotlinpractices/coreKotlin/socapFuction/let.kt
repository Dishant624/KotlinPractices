package com.example.kotlinpractices.coreKotlin.socapFuction

fun main() {

    val isEmpty : Boolean = "Dishant".let {
        customPrint(it)
        it.isEmpty()
    }

    val toLowercase  by lazy { "Dishant".let {
        customPrint(it)
        it.toLowerCase()
    }}

    println(" is empty $isEmpty")
    println(" in lowercase $toLowercase")

    fun printNonNull(str:String?){
        println("Printing $str :")

        str?.let {
            customPrint(str)
        }
    }

    printNonNull(null)
    printNonNull("This is not null")
}
// this function is call when variable is initialized but change when using lazy
fun customPrint(string: String) {
    print(string.toUpperCase())
}
