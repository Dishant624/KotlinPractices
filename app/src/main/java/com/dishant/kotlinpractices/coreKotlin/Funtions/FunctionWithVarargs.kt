package com.dishant.kotlinpractices.coreKotlin.Funtions

fun main() {

    printAll("Hello", "Dishant" ,"with" ,"All" ,"Strings", "Varargs")
    log("Hello", "Dishant" ,"with" ,"All" ,"Strings", "Varargs")

}

fun printAll(vararg messages:String){
    for (message in messages){
        print("$message ")
    }
}


/*
* At runtime, a vararg is just an array. To pass it along into a vararg parameter,
* use the special spread operator * that lets you pass in *entries (a vararg of String)
* instead of entries (an Array<String>).*/
fun log(vararg entries :String){
    printAll(*entries)
}