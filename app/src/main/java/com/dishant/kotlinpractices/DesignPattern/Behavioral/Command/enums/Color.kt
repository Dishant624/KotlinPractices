package com.dishant.kotlinpractices.DesignPattern.Behavioral.Command.enums

enum class Color(private val color: String) {

    Red("#000"),Pink("#000");

    override fun toString(): String {
        return color
    }
}
