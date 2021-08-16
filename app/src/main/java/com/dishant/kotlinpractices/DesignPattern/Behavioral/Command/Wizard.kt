package com.dishant.kotlinpractices.DesignPattern.Behavioral.Command

import android.util.Log
import java.util.*

class Wizard{

    private val undoStack: Deque<Command> = LinkedList<Command>()

    private val redoStack: Deque<Command> = LinkedList<Command>()


    fun castSpell(command: Command, target: Target){
        command.execute(target)
        undoStack.offerLast(command)
    }


    fun undoLastSpell():Boolean{
        return if(!undoStack.isEmpty()){
            val previousSpell = undoStack.pollLast()
            redoStack.offerLast(previousSpell)
            previousSpell?.undo()
            true
        }else{
            Log.d(MainActivity.TAG ,"undo stack is empty")
            false
        }
    }

    fun redoLastSpell():Boolean{
        return if(!redoStack.isEmpty()){
            val previousSpell = redoStack.pollLast()
            undoStack.offerLast(previousSpell)
            previousSpell?.redo()
            true
        }else{
            Log.d(MainActivity.TAG,"redo stack is empty")
            false
        }
    }
}