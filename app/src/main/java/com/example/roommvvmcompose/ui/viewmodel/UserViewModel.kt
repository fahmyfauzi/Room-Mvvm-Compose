package com.example.roommvvmcompose.ui.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roommvvmcompose.data.RoomResult
import com.example.roommvvmcompose.data.model.UserEntity
import com.example.roommvvmcompose.data.repository.UserRepository
import com.example.roommvvmcompose.ui.state.RoomUserState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repository: UserRepository,
    @ApplicationContext private val context: Context
) :ViewModel(){

    private val _userState = MutableStateFlow(RoomUserState())

    val userState:StateFlow<RoomUserState> = _userState.asStateFlow()

    init {
        getAllUser()
    }

    fun addUser(user:UserEntity) = viewModelScope.launch{
        repository.addUser(user).collect{result->
            when(result){
                is RoomResult.Success ->{
                    Toast.makeText(context,result.data,Toast.LENGTH_SHORT).show()
                }
                is RoomResult.Error -> {
                    Toast.makeText(context,result.exception.message,Toast.LENGTH_SHORT).show()
                }
                is RoomResult.Loading -> {

                }
            }
        }
    }

    fun updateUser(user:UserEntity) = viewModelScope.launch {
        repository.updateUser(user).collect{result->
            when(result){
                is RoomResult.Success ->{
                    Toast.makeText(context,result.data,Toast.LENGTH_SHORT).show()
                }
                is RoomResult.Error -> {
                    Toast.makeText(context,result.exception.message,Toast.LENGTH_SHORT).show()
                }
                is RoomResult.Loading -> {

                }
            }
        }
    }

    fun deleteUser(user:UserEntity) = viewModelScope.launch {
        repository.deleteUser(user).collect{result->
            when(result){
                is RoomResult.Success ->{
                    Toast.makeText(context,result.data,Toast.LENGTH_SHORT).show()
                }
                is RoomResult.Error -> {
                    Toast.makeText(context,result.exception.message,Toast.LENGTH_SHORT).show()
                }
                is RoomResult.Loading -> {

                }
            }
        }
    }

    fun getAllUser() = viewModelScope.launch {
        repository.getAllUser().collect{result->
            when(result){
                is RoomResult.Success ->{
                    _userState.update {
                        it.copy(data=result.data,isLoading = false, errorMsg = null)
                    }
                }
                is RoomResult.Error ->{
                    _userState.update {
                        it.copy(data = null,isLoading = false,errorMsg = result.exception.message)
                    }
                }
                is RoomResult.Loading -> {
                    _userState.update {
                        it.copy(data = null,isLoading = true,errorMsg = null)
                    }
                }
            }
        }
    }

}