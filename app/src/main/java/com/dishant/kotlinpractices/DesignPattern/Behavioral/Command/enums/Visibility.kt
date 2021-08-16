package com.dishant.kotlinpractices.DesignPattern.Behavioral.Command.enums

enum class Visibility(private val visibility: String) {
    INVISIBLE("Invisible"),VISIBLE("Visible");

    override fun toString(): String {
        return visibility
    }
}