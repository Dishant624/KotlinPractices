package com.dishant.kotlinpractices.coreKotlin.solid

// Dependency inversion principle

//This principle states that high-level modules should not depend on low-level modules.
// Both should depend on abstractions and Abstractions should not depend upon details.
// Details should depend upon abstractions.

class AndroidDeveloper{
    fun developMobileApp(){
        println("Developing android application by using kotlin")
    }
}

class IosDeveloper(){
    fun developMobileApp(){
        println("Developing ios application by using swift")
    }
}

fun main(){
    val androidDeveloper = AndroidDeveloper()
    val iosDeveloper = IosDeveloper()

    androidDeveloper.developMobileApp()
    iosDeveloper.developMobileApp()
}