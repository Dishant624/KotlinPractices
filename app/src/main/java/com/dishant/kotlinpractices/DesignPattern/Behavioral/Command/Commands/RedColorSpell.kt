package com.dishant.kotlinpractices.DesignPattern.Behavioral.Command.Commands

import com.dishant.kotlinpractices.DesignPattern.Behavioral.Command.enums.Color
import com.dishant.kotlinpractices.DesignPattern.Behavioral.Command.Command
import com.dishant.kotlinpractices.DesignPattern.Behavioral.Command.Target

class RedColorSpell : Command() {

    private var target: Target? = null

    override fun execute(target: Target) {
        target.color =
            Color.Red
        this.target = target
    }

    override fun undo() {
        if (target != null) {
            target?.color =
                Color.Pink
        }
    }

    override fun redo() {
        if (target != null) {
            target?.color =
                Color.Red
        }
    }

}