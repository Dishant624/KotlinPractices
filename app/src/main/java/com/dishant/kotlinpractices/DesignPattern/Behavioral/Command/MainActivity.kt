package com.dishant.kotlinpractices.DesignPattern.Behavioral.Command

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.dishant.kotlinpractices.DesignPattern.Behavioral.Command.Commands.*
import com.dishant.kotlinpractices.DesignPattern.Behavioral.Command.enums.Color
import com.dishant.kotlinpractices.DesignPattern.Behavioral.Command.enums.Size
import com.dishant.kotlinpractices.DesignPattern.Behavioral.Command.enums.Visibility
import com.dishant.kotlinpractices.R
import kotlinx.android.synthetic.main.activity_main3.*;


/** Advantages
 * Makes our code extensible as we can add
new commands without changing existing code.
Reduces coupling the invoker and receiver of a command.*/

//DisAdvantages
//Increase in the number of classes for each individual command
class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        Log.d(TAG, "ok")
        textView.text = "Hi Bro!"

        val wizard = Wizard()
        val goblinText = Goblin()

        changeTextView(goblinText)

        undo_btn.setOnClickListener {
            if (wizard.undoLastSpell()) {
                changeTextView(goblinText)
                Toast.makeText(applicationContext, goblinText.printStatus(), Toast.LENGTH_LONG)
                    .show()
            } else {
                Toast.makeText(applicationContext, "undo stack is empty", Toast.LENGTH_LONG).show()
            }

        }

        redo_btn.setOnClickListener {
            if (wizard.redoLastSpell()) {
                changeTextView(goblinText)
                Toast.makeText(applicationContext, goblinText.printStatus(), Toast.LENGTH_LONG)
                    .show()
            } else {
                Toast.makeText(applicationContext, "redo stack is empty", Toast.LENGTH_LONG).show()
            }
        }


        small_size_btn.setOnClickListener {
            wizard.castSpell(ShrinkSpell(), goblinText)
            changeTextView(goblinText)

            Toast.makeText(applicationContext, goblinText.printStatus(), Toast.LENGTH_LONG).show()
        }

        normall_size_btn.setOnClickListener {
            wizard.castSpell(ExpandSpell(), goblinText)
            changeTextView(goblinText)
            Toast.makeText(applicationContext, goblinText.printStatus(), Toast.LENGTH_LONG).show()
        }

        visible_btn.setOnClickListener {
            wizard.castSpell(VisibleSpell(), goblinText)
            changeTextView(goblinText)

            Toast.makeText(applicationContext, goblinText.printStatus(), Toast.LENGTH_LONG).show()
        }

        invisible_btn.setOnClickListener {
            wizard.castSpell(InvisibleSpell(), goblinText)
            changeTextView(goblinText)
            Toast.makeText(applicationContext, goblinText.printStatus(), Toast.LENGTH_LONG).show()
        }

        red_btn.setOnClickListener {
            wizard.castSpell(RedColorSpell(), goblinText)
            changeTextView(goblinText)
            Toast.makeText(applicationContext, goblinText.printStatus(), Toast.LENGTH_LONG).show()
        }

        pink_btn.setOnClickListener {
            wizard.castSpell(PinkColorSpell(), goblinText)
            changeTextView(goblinText)
            Toast.makeText(applicationContext, goblinText.printStatus(), Toast.LENGTH_LONG).show()
        }

    }

    private fun changeTextView(goblinText: Goblin) {
        if (goblinText.size == Size.NORMAL) {
            textView.textSize = 20F
            small_size_btn.isEnabled = true
            normall_size_btn.isEnabled = false
        } else {
            textView.textSize = 12F
            small_size_btn.isEnabled = false
            normall_size_btn.isEnabled = true
        }


        if (goblinText.visibility == Visibility.VISIBLE) {
            textView.visibility = View.VISIBLE
            visible_btn.isEnabled = false
            invisible_btn.isEnabled = true
        } else {
            textView.visibility = View.INVISIBLE
            visible_btn.isEnabled = true
            invisible_btn.isEnabled = false
        }

        if (goblinText.color == Color.Pink) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                textView.setTextColor(resources.getColor(R.color.pink, null))
            }
            red_btn.isEnabled = true
            pink_btn.isEnabled = false
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                textView.setTextColor(resources.getColor(R.color.blue, null))
            }
            red_btn.isEnabled = false
            pink_btn.isEnabled = true
        }
    }


}