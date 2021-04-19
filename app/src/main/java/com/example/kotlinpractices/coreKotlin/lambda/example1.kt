package com.example.kotlinpractices.coreKotlin.lambda

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Duration
import java.time.Instant


object BachMark{
    @RequiresApi(Build.VERSION_CODES.O)
    fun realtime(body : ()->Unit) : Duration{
        val start = Instant.now();
        try {
            body()
        }finally {
            val end = Instant.now()
            return Duration.between(start,end)
        }
    }
}
fun main() {


}