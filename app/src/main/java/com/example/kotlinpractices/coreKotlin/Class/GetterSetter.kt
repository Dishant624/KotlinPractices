package com.example.kotlinpractices.coreKotlin.Class

class Company {


    var name: String = ""
        get() = "company name : $field"                     // getter
        set(value) {
            field = value
        }      // setter

    val address: String = "Ahmadabad"
        get() = field                     // getter
//        set(value) { field = value }    //val property cannot have setter method

    var type = "IT Company"
        get() {
            return "company type : $field"
        }
        private set

    val isEmpty: Boolean
        get() = this.name.isEmpty()

    val isEmpty2 get() = this.name.isEmpty() // has type Boolean

    fun changeCompanyType(type: String) {
        this.type = type
    }
}

fun main() {

    val c = Company()
    c.name = "livebird"
    val companyName = c.name
    print(companyName)

    println()

    val companyAddress = c.address
    println(companyAddress)

    c.changeCompanyType("IT software")
    println(c.type)


}