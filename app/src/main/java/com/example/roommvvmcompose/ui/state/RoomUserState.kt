package com.example.roommvvmcompose.ui.state

import com.example.roommvvmcompose.data.model.UserEntity

data class RoomUserState(
    val data : List<UserEntity> ? = null,
    val isLoading: Boolean = false,
    val errorMsg : String? = null
)
