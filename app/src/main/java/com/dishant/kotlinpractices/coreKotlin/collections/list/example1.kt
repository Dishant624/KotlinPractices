package com.dishant.kotlinpractices.coreKotlin.collections.list


data class FamilyMember(val firstName : String , val lastName : String , val age : Int) : Comparable<FamilyMember>{
    override fun compareTo(other: FamilyMember): Int {
        val fName = firstName.compareTo(other.firstName)
        val lName = lastName.compareTo(other.lastName)
        return lName.compareTo(fName)
    }

}

data class Student(val id : Int , val name :String) : Comparable<Student>{
    override fun compareTo(other: Student): Int {
        return compareValuesBy(this, other, {it.id} , {it.name})
    }

}

fun main(){
//    listExample()
//    setExample()

//    listSorting()

    val setData = hashSetOf(1,2,3,3)
     println(setData)


}

private fun listSorting() {
    val members = mutableListOf<FamilyMember>(
        FamilyMember("Bob", "Belcher", 45),
        FamilyMember("Linda", "Celcher", 40),
        FamilyMember("Linda", "Belcher", 41),
        FamilyMember("Linda", "Aelcher", 42),
        FamilyMember("Tina", "Belcher", 43),
        FamilyMember("Gene", "Belcher", 44),
        FamilyMember("Louise", "Belcher", 45),
    )

    println("Before sort")
    println(members)

    members.sort()

    println(members)

    members.sortBy { it.age }
    println(members)
}


private fun listExample() {
    // immutable collection means that it supports only read-only functionalities and cant not modified its elements.
    val immutableList = listOf<String>("Mahipal", "Nikhil", "Rahul")

    //gives compile time error
    // immutableList.add("xyz")

    for (elementString in immutableList) {
        println(elementString)
    }

    val mutableList = mutableListOf<String>("Mahipal", "Nikhil", "Rahul")
    mutableList.add("xyz")

    for (elementString in mutableList) {
        println(elementString)
    }
}

fun setExample(){
    val immutableSet = setOf<Any>(6,9,9,0,0,"Mahipal","Nikhil")

    for (anyData in immutableSet) {
        println(anyData)
    }

    val mutableSet = mutableSetOf<Any>(234,43,53,43,35,"dfgd","dfgfdkgn","dfgkndfk")
    for (any in mutableSet) {
        println(any)
    }

    val student1 = Student(1, "First")
    val student2 = Student(1, "First")
    val mutableStudent = mutableSetOf<Student>(student1,student2)

    for (student in mutableStudent) {
        println("${student.id}")
    }
}