package com.example.roommvvmcompose.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "room_user_table")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int? = null,
    val username:String
)
