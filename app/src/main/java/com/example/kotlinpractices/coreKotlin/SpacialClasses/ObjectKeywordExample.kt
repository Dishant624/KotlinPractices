package com.example.kotlinpractices.coreKotlin.SpacialClasses


//object as Expression

fun rentPrice(standardDays: Int, festivalDays: Int, spacialDays: Int): Int {

    val dayRates = object {
        val standard = 30 * standardDays
        val festival = 50 * festivalDays
        val spacial = 100 * spacialDays
    }

    return dayRates.standard+dayRates.festival+dayRates.spacial
}

fun main() {

    println(rentPrice(10,5,1))
}