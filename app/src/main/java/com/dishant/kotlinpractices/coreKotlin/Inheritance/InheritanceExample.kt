package com.dishant.kotlinpractices.coreKotlin.Inheritance


//kotlin classes are final by default. If you want to allow
// the class inheritance, mark the class with the open modifier.
open class Dog(){

    //Kotlin methods are also final by default.
    //As with the classes, the open modifier allows overriding them.
    open fun sayHello(){
        println("Wow Wow")
    }
}

class Pupi : Dog(){

    override fun sayHello() {
        println("wif wif")
    }
}

//inheritance with parameterrized constructor
open class Tiger(val origin:String){

    fun sayHello(){
        println("A Tiger from $origin say grrhh!")
    }
}

class IndianTiger() : Tiger("India")

class AfricanTiger(originname : String) : Tiger(origin = originname)

fun main() {

    val dog :Dog = Pupi()
    dog.sayHello();

    val indianTiger =IndianTiger();
    println(indianTiger.origin)

    val africanTiger = AfricanTiger("Africa")
    println(africanTiger.origin)

}