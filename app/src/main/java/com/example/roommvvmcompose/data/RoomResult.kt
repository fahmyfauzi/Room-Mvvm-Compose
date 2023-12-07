package com.example.roommvvmcompose.data

import java.lang.Exception

sealed class RoomResult <out R >{
    data class Success<out T> (val data:T):RoomResult<T>()
    data class Error(val exception: Throwable): RoomResult<Nothing>()

    object Loading:RoomResult<Nothing>()
}
