package com.dishant.kotlinpractices.coreKotlin.delegate.implicitDelegation

fun main(){

    val person = Person(JacKName(), LogisticRunner())
    println(person.name)
    person.run()

}

interface  Nameable{
    var name:String
}

class JacKName : Nameable{
    override var name: String = "Jack"
}

class LogisticRunner : Runnable{
    override fun run() {
        println("long")
    }
}

class Person(name: Nameable,runner: LogisticRunner) : Nameable by name , Runnable by runner