package com.example.kotlinpractices.coreKotlin.SpacialClasses

sealed class Mammal(val name:String)

class Cat(catName : String ): Mammal(catName)

class Human(humanName : String, val job :String) :Mammal(humanName)


//if we check case by sealed class than else part is not required in when expression
//but we pass open class as case else part required
fun greetMammal(mammal:Mammal): String {
    return  when(mammal){
        is Cat -> "Hello ${mammal.name}"
        is Human -> "Hello ${mammal.name} You are working as ${mammal.job}"
    }
}
fun main() {

    println(greetMammal(Cat("mimi")))
    println(greetMammal(Human("Bob","Developer")))

}