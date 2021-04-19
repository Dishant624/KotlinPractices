package com.example.kotlinpractices.coreKotlin.delegate

import kotlin.properties.Delegates

class TestDelegateProperty{

    var number by Delegates.notNull<Int>()

    companion object{
        var fullNameOfPerson by TrimDelegate()
        var fullNameOfPerson1 : String = ""
    }

   fun setAndGetValue() {
       fullNameOfPerson = "Dishant v Patel "
       fullNameOfPerson1 = "Dishant v Patel  "
       print(fullNameOfPerson)
       print(fullNameOfPerson1)
       print(fullNameOfPerson)
       print(fullNameOfPerson1)
   }
}

fun main(args:Array<String>){
     val testDelegateProperty = TestDelegateProperty()
    testDelegateProperty.setAndGetValue()

}