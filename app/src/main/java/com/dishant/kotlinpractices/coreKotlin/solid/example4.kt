package com.dishant.kotlinpractices.coreKotlin.solid

// interface segregation principle

// this principal states that once an interface  becomes too fat, it need to be spilt into smaller interface so that client
// of the interface will only know about the methods that pretrain to them

interface  Animal{
    fun eat()
    fun sleep()
    fun fly()
}

class Cat : Animal{
    override fun eat() {
        println("eat")
    }

    override fun sleep() {
        println("sleep")
    }

    override fun fly() {
        TODO("Not yet implemented")
    }

}

class  Bird : Animal{
    override fun eat() {
        println("eat")
    }

    override fun sleep() {
        println("sleep")
    }

    override fun fly() {
        println("fly")
    }

}


//solution

interface Animal1{
    fun sleep()
    fun eat()
}

interface FlyingAnimal1{
    fun fly()
}

class Cat1 : Animal1{
    override fun sleep() {
        println("sleep")
    }

    override fun eat() {
        println("eat")
    }
}

class Bird1 : Animal1,FlyingAnimal1{
    override fun sleep() {
        println("sleep")
    }

    override fun eat() {
        println("eat")
    }

    override fun fly() {
        println("fly")
    }

}


