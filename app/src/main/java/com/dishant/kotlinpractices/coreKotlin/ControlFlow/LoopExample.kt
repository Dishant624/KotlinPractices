package com.dishant.kotlinpractices.coreKotlin.ControlFlow

fun main() {
    val cakes = listOf("chocolate", "chess", 1)

    for (data in cakes) {
        println(data)
    }

    val zoo = Zoo(listOf(Animal("tiger"), Animal("lion")))

    for (animal in zoo) {
        println(animal.name)
    }
}

class Animal(val name: String)

class Zoo(private val animals: List<Animal>) {
    operator fun iterator(): Iterator<Animal> {
        return animals.iterator()
    }
}

