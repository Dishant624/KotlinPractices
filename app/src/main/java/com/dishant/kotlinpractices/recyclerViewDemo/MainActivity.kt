package com.dishant.kotlinpractices.recyclerViewDemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dishant.kotlinpractices.R
import com.dishant.kotlinpractices.recyclerViewDemo.fragment.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_recyclerview_demo)
        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.container,MainFragment())
                .commitNow()
        }
    }
}