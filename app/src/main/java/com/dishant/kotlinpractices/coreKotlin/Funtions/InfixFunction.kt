package com.dishant.kotlinpractices.coreKotlin.Funtions

import android.media.MediaRecorder

fun main(args: Array<String>) {

    println(3 time "Hello ")

    val pair = "pen" to "Akshay"
    println(pair)

    val (obj, person) = pair
    println("$person has $obj")

    //Here's your own implementation of to creatively called has.
    println("Akshay" has "pen")


    val pen = Objects("Pen")
    val book = Objects("Book")

    val Akshay = Person("Akshay")

    //Infix notation also works on members functions (methods).
    Akshay has pen
    Akshay has book

    for (objcts in Akshay.hasObjects){
        println(objcts.name)
    }
//    var MediaRecorder =MediaRecorder()
//    MediaRecorder.start()
    MediaRecorder.AudioSource.MIC
}


/*Infix notation
Functions marked with the infix keyword can also be called using the infix
notation (omitting the dot and the parentheses for the call).
Infix functions must satisfy the following requirements:

They must be member functions or extension functions;
They must have a single parameter;
The parameter must not accept variable number of arguments and must have no default value.*/
infix fun Int.time(str:String ) = str.repeat(this);

infix fun String.has(str: String) = "${Pair(this,str)}"

class Person(val name: String){
    val hasObjects = mutableListOf<Objects>()

    //The containing class becomes the first parameter.
    infix fun has(other:Objects){hasObjects.add(other)}
}

class Objects(val name:String)