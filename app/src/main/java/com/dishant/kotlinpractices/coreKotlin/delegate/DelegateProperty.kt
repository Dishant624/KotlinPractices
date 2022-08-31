package com.dishant.kotlinpractices.coreKotlin.delegate

import android.view.autofill.AutofillValue
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

fun main(){
    val example = Example()
    println(example.someName)
//    println(example.name)

    example.mutable = "Data is fixed"
    print(example.mutable)

}

class Example{
    val someName by NameDelegate()

//    throw exception while value is null
    var name : String by Delegates.notNull()

    var mutable by SimpleDelegate()
}

class NameDelegate{
    operator fun getValue ( thisRef : Any? , property : KProperty<*>) : String{
        val returnValue = property.name
        return returnValue;
    }
}

class SimpleDelegate{
    var value : String = ""

    operator fun getValue (thisRef : Any , property: KProperty<*>) : String{
        return value
    }

    operator fun setValue(thisRef: Any , property: KProperty<*>,value: String){
        println("you pass me $value")
        this.value = value;
    }
}