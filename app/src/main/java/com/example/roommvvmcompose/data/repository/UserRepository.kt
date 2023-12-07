package com.example.roommvvmcompose.data.repository

import com.example.roommvvmcompose.data.RoomResult
import com.example.roommvvmcompose.data.model.UserEntity
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun addUser(user:UserEntity) : Flow<RoomResult<String>>

    suspend fun updateUser(user: UserEntity): Flow<RoomResult<String>>

    suspend fun deleteUser(user: UserEntity) : Flow<RoomResult<String>>

    suspend fun getAllUser():Flow<RoomResult<List<UserEntity>>>
}