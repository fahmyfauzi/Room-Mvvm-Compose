package com.example.roommvvmcompose.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.roommvvmcompose.data.dao.UserDao
import com.example.roommvvmcompose.data.model.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 1
)
abstract class UserDatabase :RoomDatabase(){
    abstract fun userDao() : UserDao
}