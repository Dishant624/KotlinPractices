package com.example.kotlinpractices.coreKotlin.SpacialClasses

data class User(val name:String , val  id : Int)
fun main() {

    val firstUser = User("Alex" ,1)
    println(firstUser)

    val secondUser = User("Alex",1)
    val thirdUser = User("Max",2)

    println("${secondUser==firstUser}")
    println("${secondUser==thirdUser}")


    //Equal data class instances have equal hashcode
    println(firstUser.hashCode())
    println(secondUser.hashCode())
    println(thirdUser.hashCode())


    val fourth = firstUser.copy()
    println(fourth)

    println(fourth.hashCode())

    //Auto generated componetN function let you get values
    // of properties in the order of declaration
    println("Name = ${firstUser.component1()}")
    println("Id = ${firstUser.component2()}")



}

