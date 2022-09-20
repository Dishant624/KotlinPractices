package com.dishant.kotlinpractices.customview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dishant.kotlinpractices.R

class CustomViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_view)
    }
}