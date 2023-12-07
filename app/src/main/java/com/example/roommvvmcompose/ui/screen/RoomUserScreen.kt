package com.example.roommvvmcompose.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.roommvvmcompose.data.model.UserEntity
import com.example.roommvvmcompose.ui.viewmodel.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoomUserScreen(
    viewModel: UserViewModel = hiltViewModel()
){
    val userState by viewModel.userState.collectAsState()

    var inputName by remember { mutableStateOf("") }

    Column(
        Modifier.fillMaxSize()
    ) {
        TextField(
            value = inputName, onValueChange = { inputName = it },
            label = {Text("Name")}
            )
        Button(onClick = {
            viewModel.addUser(UserEntity(username = inputName))
        }) {
            Text(text = "submit")
        }

        when{
            userState.isLoading->{
                CircularProgressIndicator(
                    modifier =  Modifier.align(Alignment.CenterHorizontally)
                )
            }
            !userState.errorMsg.isNullOrEmpty() -> {
                Text(text = userState.errorMsg ?: "Error")
            }

            userState.data.isNullOrEmpty() ->{
                Text(text = "Empty")
            }
            userState.data != null ->{
                LazyColumn{
                    items(userState.data!!){item->
                        RoomUserListItem(
                            username =item.username,
                            userId = item.id.toString(),
                            onUpdateClick ={id,name ->
                                viewModel.updateUser(UserEntity(id.toInt(),name))
                            },
                            onDeleteClick = {id,name->
                                viewModel.deleteUser(UserEntity(id.toInt(),name))
                            }
                        )
                    }
                }
            }
        }

    }
}