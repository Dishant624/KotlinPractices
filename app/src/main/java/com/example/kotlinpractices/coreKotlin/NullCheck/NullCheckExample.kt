package com.example.kotlinpractices.coreKotlin.NullCheck

fun main(args: Array<String>) {

    //non-null variable
    var neverNull:String = "This cannot be null"

    //null can not be a value of non-null
    // neverNull =null


    var nullable:String? =" This can be null"

    nullable = null

    var inferredNonNull = " The compiler assume  non-null"

    //null can not be a value of non-null
    //inferredNonNull = null

    stringLength("ok")
    //null can not be a value of non-null
    //stringLength(null)

    val dec = describeString("hello india.")
    println(dec)

}

fun stringLength(notNull:String): Int {
    return notNull.length
}

fun describeString(maybeNull : String?): String {
    if(maybeNull !=null && maybeNull.isNotEmpty()){
        return "String of length ${maybeNull.length}"
    }else{
        return "Empty or Null String"
    }
}
