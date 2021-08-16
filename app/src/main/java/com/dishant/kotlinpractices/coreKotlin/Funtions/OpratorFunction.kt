package com.dishant.kotlinpractices.coreKotlin.Funtions

fun main(args: Array<String>) {

    println(3 * "Hello ")

    val str ="Hello bro !";

    println(str[0..10])

    println("Dishant " + "Hello last")

}

/*https://kotlinlang.org/docs/reference/operator-overloading.html?&_ga=2.71935891.1021303440.1584340127-1671018103.1584340127#indexed
*
* Certain functions can be "upgraded" to operators, allowing their calls with the corresponding operator symbol.
*
* like below Binary Arithmetic oprators coresponding methods
a + b	a.plus(b)
a - b	a.minus(b)
a * b	a.times(b)
a / b	a.div(b)
a % b	a.rem(b), a.mod(b) (deprecated)
a..b	a.rangeTo(b)
* */
operator fun Int.times(str:String) = str.repeat(this)

operator fun String.plus(str: String) = this.plus("Hello $str")

operator fun String.get(range: IntRange) = substring(range)