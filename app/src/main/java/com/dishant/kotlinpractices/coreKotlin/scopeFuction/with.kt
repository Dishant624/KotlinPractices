package com.dishant.kotlinpractices.coreKotlin.scopeFuction

class Configuration(var host:String , var port :String)

fun main() {

    var configuration  =
        Configuration(
            host = "127.0.0.0",
            port = "8080"
        )

    with(configuration){
        println("$host:$port")
    }

//    we change property of object
//   like apply, with is used to change instance properties
//   without the need to call dot operator over the reference every time.
     configuration =with(configuration){
        host = "198.168.0.43"
        port = "9090"
        println("$host : $port")
        this
    }



    // we can return value using with

    var isConfigurationChange = with(configuration){
        host = "198.168.0.43"
        port = "9080"
        true
    }

    println("cofigurationChange $isConfigurationChange")

//    use with insteadof below
    print("${configuration.host} : ${configuration.port}")

}