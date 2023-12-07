package com.example.roommvvmcompose.data.repository

import com.example.roommvvmcompose.data.RoomResult
import com.example.roommvvmcompose.data.dao.UserDao
import com.example.roommvvmcompose.data.model.UserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import java.lang.Exception
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao:UserDao
) : UserRepository {
    override suspend fun addUser(user: UserEntity): Flow<RoomResult<String>> = flow {
        emit(RoomResult.Loading)
        try {
            userDao.addUser(user)
            emit(RoomResult.Success("User added!"))
        }catch (e:Exception){
            emit(RoomResult.Error(Throwable(e.message)))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun updateUser(user: UserEntity): Flow<RoomResult<String>> = flow{
       emit(RoomResult.Loading)
        try {
            userDao.updateUser(user)
            emit(RoomResult.Success("User Updated"))
        }catch (e:Exception){
            emit(RoomResult.Error(Throwable(e.message)))
        }
    }.flowOn(Dispatchers.IO)


    override suspend fun deleteUser(user: UserEntity): Flow<RoomResult<String>> = flow{
        emit(RoomResult.Loading)
        try {
            userDao.deleteUser(user)
            emit(RoomResult.Success("User Deleted!"))
        }catch (e:Exception){
            emit(RoomResult.Error(Throwable(e.message)))
        }
    }

    override suspend fun getAllUser(): Flow<RoomResult<List<UserEntity>>> = callbackFlow<RoomResult<List<UserEntity>>> {
        userDao.getAllUsers().onStart {
            trySend(RoomResult.Loading)
        }.catch { e->
            trySend(RoomResult.Error(Throwable(e.message)))
        }.collectLatest { list->
            trySend(RoomResult.Success(list))
        }
        awaitClose{ close()}
    }.flowOn(Dispatchers.IO)
}