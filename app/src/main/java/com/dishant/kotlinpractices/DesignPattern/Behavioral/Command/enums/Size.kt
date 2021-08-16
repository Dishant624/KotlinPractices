package com.dishant.kotlinpractices.DesignPattern.Behavioral.Command.enums

enum class Size(private val title: String) {

    SMAll("small"), NORMAL("NORMAL");

    override fun toString(): String {
        return title;
    }
}