package com.dishant.kotlinpractices.DesignPattern.Behavioral.Command

import com.dishant.kotlinpractices.DesignPattern.Behavioral.Command.enums.Color
import com.dishant.kotlinpractices.DesignPattern.Behavioral.Command.enums.Size
import com.dishant.kotlinpractices.DesignPattern.Behavioral.Command.enums.Visibility

class Goblin : Target() {

    init {
        size = Size.NORMAL
        visibility =
            Visibility.VISIBLE
        color = Color.Pink
    }

    override fun toString(): String {
        return "Goblin"
    }

}