package com.example.kotlinpractices.coreKotlin.multiThreading

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.kotlinpractices.R
import kotlinx.android.synthetic.main.activity_thread_example.*

class ThreadExampleActivity : AppCompatActivity() {

    var handlerUIThread : Handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread_example)



//        Thread(startThread()).apply { start() }
//        ExampleThread(10).apply { start() }
        Thread(ExampleRunnable(10)).apply { start() }
    }

    fun startThread() : Runnable{
        return Runnable {
            for (number in 1..10){
                println("startThread $number")
                Thread.sleep(1000)
            }
        }
    }

    class ExampleThread(private var seconds : Int) : Thread(){
        override fun run() {
            super.run()
            for (number in 1..seconds){
                println("ExampleThread startThread $number")
                Thread.sleep(1000)
            }
        }
    }

    inner class ExampleRunnable(private var seconds: Int) : Runnable{
        override fun run() {
            for (number in 1..seconds){
                if(number == seconds/2){
                    /*start_thread_btn.post {
                        start_thread_btn.setText("50%")
                    }*/

                    /*
                    handlerUIThread.post(Runnable {
                        start_thread_btn.text = "50%"
                    })*/

                    runOnUiThread{

                    }
                }
                println("ExampleRunnable startThread $number")
                Thread.sleep(1000)
            }
        }

    }

}