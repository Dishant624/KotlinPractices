package com.example.kotlinpractices.coreKotlin.Class

class Customer

//  Declares a class with two properties: immutable id and mutable email,
//  and a constructor with two parameters id and email.
class Contact(val id : Int , var email : String)


//You can't put main() inside a class in Kotlin. In Java it must be static,
// and the only way to make a function static in Kotlin is to put it directly under a package.
fun main(args: Array<String>) {

//    Create instanse of class customer with default constructor.
//    there is no new keyword in kotlin
    val customer = Customer()
    val contact  = Contact(1,"email@gmail.com")

    println(contact.id)
    println(contact.email)
    contact.email = "newEmail@gamil.com"
    println(contact.email)


}