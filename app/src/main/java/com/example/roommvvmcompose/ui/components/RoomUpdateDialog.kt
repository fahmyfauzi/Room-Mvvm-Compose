package com.example.roommvvmcompose.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoomUpdateDialog(
    username:String,
    userId:String,
    showDialog:MutableState<Boolean>,
    onUpdateClick: (id:String,name:String) -> Unit
){
    var inputText by remember {
        mutableStateOf(username)
    }
    
    AlertDialog(
        onDismissRequest = { showDialog.value = false },
        text = { TextField(value = inputText, onValueChange = { inputText = it })},
        confirmButton = { 
            Button(onClick = {
                onUpdateClick(userId, inputText)
                showDialog.value =false
            }) {
                Text(text= "Update")
            }
        })

}