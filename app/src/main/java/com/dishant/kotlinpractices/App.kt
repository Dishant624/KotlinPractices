package com.dishant.kotlinpractices

import android.app.Application
import android.content.Context
import android.content.res.Resources

class App :Application(){
    var context : Context ? = null
    companion object{
        var resource :Resources ?= null
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        resource = resources
    }
}