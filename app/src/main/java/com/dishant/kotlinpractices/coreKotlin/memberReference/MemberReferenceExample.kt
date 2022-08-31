package com.dishant.kotlinpractices.coreKotlin.memberReference


class Student(val id : Int ,val name : String)

fun main(){
    val list = arrayListOf(
        Student(1,"first"),
        Student(2,"second"),
        Student(3,"third"),
        Student(4,"fourth"),
    )

    // variant 1 have issue in passing reference inside lambda
    println(list.map {
        Student::name
    })

    println(list.map {
        it.name
    })

    println(list.map(Student::name))

    val lambda : (Student) -> String = {it.name}
    println(list.map(lambda))

}


//output
//[property name (Kotlin reflection is not available), property name (Kotlin reflection is not available), property name (Kotlin reflection is not available), property name (Kotlin reflection is not available)]
//[first, second, third, fourth]
//[first, second, third, fourth]
//[first, second, third, fourth]