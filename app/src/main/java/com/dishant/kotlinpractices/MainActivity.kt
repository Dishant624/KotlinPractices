package com.dishant.kotlinpractices
//
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.util.Log
//import androidx.lifecycle.Observer
//import com.example.kotlinpractices.roomDatabase.AppDatabase
//import com.example.kotlinpractices.roomDatabase.UserEntity
//import kotlinx.coroutines.GlobalScope
//import kotlinx.coroutines.launch
//private const val TAG = "MainActivity"
//
//class MainActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        val db = AppDatabase(this)
//
//
//        Log.d(TAG, "onCreate: ");
//        Log.v(TAG, "onCreate: ");
//        Log.i(TAG, "onCreate: ");
//        GlobalScope.launch {
//            db.userDao().insertAll(UserEntity("Dishant","Patel"))
//        }
//
//        db.userDao().getAll().observe(this, Observer {
//            it.forEach {
//                Log.d(TAG,"id ${it.userId.toString()}")
//                Log.d(TAG,"name ${it.firstName}")
//                Log.d(TAG,"lastname ${it.lastName}")
//
//            }
//        })
//
//
//
//    }
//
//
//}
