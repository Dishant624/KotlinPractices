package com.example.kotlinpractices.coreKotlin.SpacialClasses


//object as Expression
class MyClass {
    var myvalues =object {
        var a : Int =10
            set(value) {
                field = value* 20
            }
        var b =20
    }

    var number = 1

    companion object{
        var myvalues = 10
    }

}
fun rentPrice(standardDays: Int, festivalDays: Int, spacialDays: Int): Int {

    val dayRates = object {
        val standard = 30 * standardDays
        val festival = 50 * festivalDays
        val spacial = 100 * spacialDays
    }

    return dayRates.standard+dayRates.festival+dayRates.spacial
}

fun main() {

//    println(rentPrice(10,5,1))

    var myClass = MyClass()
    println(myClass.number++)
    println(myClass.number++)
    println(MyClass.myvalues++)
    println(MyClass.myvalues++)
    println(MyClass().number++)
    println(MyClass().number++)
    println(MyClass().number++)
}