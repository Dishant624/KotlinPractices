package com.dishant.kotlinpractices.coreKotlin.scopeFuction


data class Person(var name : String, var age:Int , var about: String){
    constructor(): this("",0,"")
}
fun main() {

    val dishant = Person()

//    apply is an extension function on a type. It runs on the object
//    reference (also known as receiver) into the expression and returns
//    the object reference on completion.
    val stringDescription = dishant.apply {
        name = "Dishant"
        age = 27
        about = "Android developer"
    }.toString()

    println(stringDescription)




}