package com.example.kotlinpractices.coreKotlin.delegate

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class TrimDelegate : ReadWriteProperty<Any?,String>{

    private var trimValue = ""
    override fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return trimValue
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        trimValue =value.trim()
    }

}