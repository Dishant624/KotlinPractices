package com.dishant.kotlinpractices.coreKotlin.solid

// single responsibility principle

data class User(var id: Long, var name: String, var password: String) {
    fun signIn(){

    }
    fun signOut(){

    }
}

// The single responsibility principle states that every class should have one and only one responsibility
// so separate the the classes
// User class should only have one responsibility i.e hold user information.


data class User1(var id:Long, var name: String,var password : String)

class AuthenticationService(){
    fun signIn(){

    }

    fun signOut(){

    }
}
