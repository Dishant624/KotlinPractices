package com.example.kotlinpractices.roomDatabase

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {
    @Query("SELECT * FROM UserList")
    fun getAll(): LiveData<List<UserEntity>>

    @Query("SELECT * FROM UserList WHERE firstName LIKE :title")
    fun findByTitle(title: String): UserEntity

    @Insert
    fun insertAll(vararg todo: UserEntity)

    @Delete
    fun delete(todo: UserEntity)
    
    @Update
    fun updateTodo(vararg todos: UserEntity)
}