package com.dishant.kotlinpractices.DesignPattern.Behavioral.Command.Commands

import com.dishant.kotlinpractices.DesignPattern.Behavioral.Command.enums.Color
import com.dishant.kotlinpractices.DesignPattern.Behavioral.Command.Command
import com.dishant.kotlinpractices.DesignPattern.Behavioral.Command.Target

class PinkColorSpell : Command() {

    private var target: Target? = null

    override fun execute(target: Target) {
        target.color =
            Color.Pink
        this.target = target
    }

    override fun undo() {
        if (target != null) {
            target?.color =
                Color.Red
        }
    }

    override fun redo() {
        if (target != null) {
            target?.color =
                Color.Pink
        }
    }

}