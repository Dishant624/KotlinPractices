package com.dishant.kotlinpractices.coreKotlin.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dishant.kotlinpractices.R
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis

//example link
//https://proandroiddev.com/kotlin-coroutines-in-andriod-ff0b3b399fa0


/*
Let’s take an example and understand better.
Let’s create an Activity with two suspend functions with some delay and inside
 OnCreate let’s execute some logic to check how the suspend functions work.
 Have a look at the below code where GlobalScope.launch creates and launches
 a coroutine because suspend functions can’t be called directly in normal functions.
 */

@DelicateCoroutinesApi
class KotlinCoroutinesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_coroutines)

        GlobalScope.launch {
            val time = measureTimeMillis {
                val one = sampleOne()
                val two = sampleTwo()
                println("The answer is ${one +two}")
            }
            println("Completed in $time ms")
        }

        println("The End")

    }

    private suspend fun sampleOne() : Int{
        println("sampleOne ${System.currentTimeMillis()}")
        delay(1000L)
        return 10
    }

    private suspend fun sampleTwo() :Int {
        println("sampleTwo ${System.currentTimeMillis()}")
        delay(1000L)
        return 10
    }
}

//out put
//13:49:30.966 /System.out: The End
//13:49:30.967 /System.out: sampleOne 1624781970967
//13:49:31.971 /System.out: sampleTwo 1624781971971
//13:49:32.975 System.out: The answer is 20
//13:49:32.976 System.out: Completed in 2008 ms