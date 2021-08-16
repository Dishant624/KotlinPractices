package com.dishant.kotlinpractices.coreKotlin.Variables

fun main(args: Array<String>) {

    //var means variable = var
    //val means final variable or fix value or simply value = val

    //Declares a mutable  variable with initializing it.
    //mutable means variable a can be reassigned
    var a: String ="Hello"
    println(a)

    a= "Hi !"
    println(a)


    //Declares a Immutable variable with initializing it.
    //Immutable means variable b cannot be reassigned
    val b: Int= 10;

    //like
    //b=20;


    //Declares an immutable variable and initializing it without specifying the type. The compiler infers the type Int.
    val c=20;


    var e: Int
    //An attempt to use the variable causes a compiler error: Variable 'e' must be initialized.
    //println(e)


    val d: Int

    if (true) {
        d = 1
    } else {
        d = 2
    }

    //Reading the variable is possible because it's already been initialized.
    println(d)


}