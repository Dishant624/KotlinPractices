package com.example.kotlinpractices.roomDatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UserList")
data class UserEntity constructor(

    @ColumnInfo(name = "FirstName")
    val firstName: String,

    @ColumnInfo(name = "LastName")
    val lastName: String
){

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    var userId: Int? = null
}