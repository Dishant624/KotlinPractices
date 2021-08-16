package com.dishant.kotlinpractices.DesignPattern.Behavioral.Command.Commands

import com.dishant.kotlinpractices.DesignPattern.Behavioral.Command.Command
import com.dishant.kotlinpractices.DesignPattern.Behavioral.Command.Target
import com.dishant.kotlinpractices.DesignPattern.Behavioral.Command.enums.Visibility

class VisibleSpell : Command() {

    private var target: Target? = null

    override fun execute(target: Target) {
        target.visibility =
            Visibility.VISIBLE
        this.target = target
    }

    override fun undo() {
        if (target != null) {
            target?.visibility =
                Visibility.INVISIBLE
        }
    }

    override fun redo() {
        if (target != null) {
            target?.visibility =
                Visibility.VISIBLE
        }
    }

}