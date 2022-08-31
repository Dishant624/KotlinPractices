package com.dishant.kotlinpractices.surfaceview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.dishant.kotlinpractices.R

private const val TAG = "SurfaceViewActivity"

class SurfaceViewActivity : AppCompatActivity() {

    private lateinit var myView: MyView
    lateinit var bitmap: Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myView = MyView(this)
        bitmap = BitmapFactory.decodeResource(resources, R.drawable.basketball)
        setContentView(myView)

        myView.setOnClickListener {
            
        }
    }

    override fun onPause() {
        super.onPause()
        myView.pause()
    }

    override fun onResume() {
        super.onResume()
        myView.resume()
    }

    inner class MyView(context: Context?) : SurfaceView(context), Runnable,
        SurfaceHolder.Callback2 {


        private var thread: Thread? = null
        private var surfaceHold: SurfaceHolder = holder
        private var isItOk = false

        var x1: Float = 0.0f
        var y1: Float = 0.0f

        init {
            surfaceHold.addCallback(this)
        }

        override fun run() {
            while (isItOk) {
                if (surfaceHold.surface.isValid) {
                    val canvas = surfaceHold.lockCanvas();
                    canvas.drawARGB(255, 51, 51, 255)
                    canvas.drawBitmap(
                        bitmap, this.x1 - (bitmap.width / 2),
                        this.y1 - (bitmap.height / 2), null
                    )
                    surfaceHold.unlockCanvasAndPost(canvas)
                }

            }
        }

        fun pause() {
            isItOk = false
            while (true) {
                try {
                    thread?.join()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                break;
            }
            thread = null
        }

        fun resume() {
            isItOk = true
            thread = Thread(this)
            thread?.start()
        }

        @SuppressLint("ClickableViewAccessibility")
        override fun onTouchEvent(event: MotionEvent?): Boolean {
            when (event!!.action) {
                MotionEvent.ACTION_DOWN -> {
                    this.x1 = event!!.x
                    this.y1 = event!!.y
                    invalidate()
                }
                MotionEvent.ACTION_UP -> {
                    this.x1 = event!!.x
                    this.y1 = event!!.y
                    invalidate()
                }
                MotionEvent.ACTION_MOVE -> {
                    this.x1 = event!!.x
                    this.y1 = event!!.y
                    invalidate()
                }
            }
            return true
        }

        override fun surfaceCreated(holder: SurfaceHolder) {
            TODO("Not yet implemented")
        }

        override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
            TODO("Not yet implemented")
        }

        override fun surfaceDestroyed(holder: SurfaceHolder) {
            TODO("Not yet implemented")
        }

        override fun surfaceRedrawNeeded(holder: SurfaceHolder) {
            TODO("Not yet implemented")
        }


    }

}