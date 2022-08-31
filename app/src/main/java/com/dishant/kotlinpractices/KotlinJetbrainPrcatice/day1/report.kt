package com.dishant.kotlinpractices.KotlinJetbrainPrcatice.day1

import java.io.File

//video tutorial link
//https://www.youtube.com/playlist?list=PLlFc5cFwUnmwfLRLvIM7aV7s73eSTL005

fun main(){
    val numbers = File("/Users/admin/LivebirdProjects/PracticeProjets/KotlinPractices/app/src/main/java/com/dishant/kotlinpractices/KotlinJetbrainPrcatice/day1/input.txt")
        .readLines()
        .map(String::toInt)

    for (first in numbers){
        for (second in numbers){
            for (third in numbers){
                if(first + second + third== 2020){
                    println("$first ,$second , $third ")
                    println(first * second * third)
                    return
                }
            }
        }
    }
}