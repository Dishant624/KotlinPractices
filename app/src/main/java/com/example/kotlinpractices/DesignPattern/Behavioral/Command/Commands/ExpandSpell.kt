package com.example.kotlinpractices.DesignPattern.Behavioral.Command.Commands

import com.example.kotlinpractices.DesignPattern.Behavioral.Command.Command
import com.example.kotlinpractices.DesignPattern.Behavioral.Command.Target
import com.example.kotlinpractices.DesignPattern.Behavioral.Command.enums.Size


class ExpandSpell : Command() {

    private var oldSize: Size? =null

    private var target: Target? =null

    override fun execute(target: Target) {

        oldSize = target.size
        target.size =
            Size.NORMAL
        this.target = target
    }

    override fun undo() {
        if(oldSize!=null && target!= null){
            val tempSize = target?.size
            target?.size= oldSize
            oldSize = tempSize
        }

    }

    override fun redo() {
        undo()
    }

}