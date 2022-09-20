package com.dishant.kotlinpractices.coreKotlin.solid

// LisKov Substitution Principle

abstract class Vehicle{
    protected var isEngineWorking = false
    abstract fun startEngine()
    abstract fun stopEngine()
    abstract fun moveForward()
    abstract fun moveBack()
}

class Car : Vehicle() {
    override fun startEngine() {
        println("Engine start")
        isEngineWorking = true
    }

    override fun stopEngine() {
        println("Engine stopped")
        isEngineWorking = false
    }

    override fun moveForward() {
        println("Moving Forward")
    }

    override fun moveBack() {
        println("Moving back")
    }

}


class Bicycle : Vehicle(){
    override fun startEngine() {
        TODO("Not yet implemented")
    }

    override fun stopEngine() {
        TODO("Not yet implemented")
    }

    override fun moveForward() {
        println("Move Forward")
    }

    override fun moveBack() {
        println("Move Back")
    }
}


// Bicycle is not implemented all method of super class method

// solution

abstract class Vehicle1 {
    abstract fun moveForward()
    abstract fun moveBack()
}

abstract class Vehicle1WithEngine : Vehicle1(){
    protected var isEngineWorking = false
    abstract fun startEngine()
    abstract fun stopEngine()
}


class car1 : Vehicle1WithEngine(){
    override fun startEngine() {
        isEngineWorking = true
    }

    override fun stopEngine() {
        isEngineWorking = false
    }

    override fun moveForward() {
        println("Move Forward")
    }

    override fun moveBack() {
        println("Move Back")
    }
}


class Bicycle1 : Vehicle1(){
    override fun moveForward() {
        println("move forward")
    }

    override fun moveBack() {
        println("move back")
    }

}