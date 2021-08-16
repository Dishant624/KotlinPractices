package com.dishant.kotlinpractices.coreKotlin.extra

fun main() {
    printMultiplicationTable(1..10, 1..20)
}

fun printMultiplicationTable(rows: IntRange, columns:IntRange){
    for(row in rows){
        for (column in columns){
            print("$column * $row =")

            if(row<10) {
                System.out.format("%-8d", row * column)
            }else{
                System.out.format("%-7d", row * column)
            }

        }
        println()
    }

}