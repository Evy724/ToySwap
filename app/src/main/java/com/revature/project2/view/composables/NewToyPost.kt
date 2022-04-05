package com.revature.project2.view.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


//@Composable
//fun postNewToy(){
//    Image(modifier = Modifier
//        .padding(10.dp)
//        .fillMaxWidth()
//        .height(100.dp)
//        .wrapContentHeight(),
//
//        shape= CircleShape,
//        onClick = { println("Intent to img upload prompt") })

@Composable
fun screenTitle() {
    Text("New Toy Post", fontSize = 40.sp, modifier = Modifier
        .padding(10.dp)
        .wrapContentHeight(), textAlign = TextAlign.Center)
}

@Composable
fun inputToyName() {
    var text by remember{ mutableStateOf("")}

    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        label = { Text("Toy Name")},
        modifier = Modifier
            .padding(10.dp)
            .wrapContentHeight()
    )

}

@Composable
fun toyDescription() {
    var value by remember { mutableStateOf(" \n \n \n ") }

    TextField(
        value = value,
        onValueChange = { value = it },
        label = { Text("Toy Description") },
        maxLines = 4,
        modifier = Modifier.padding(10.dp)
    )
}

@Composable
fun ageOfToy() {
    var text by remember{ mutableStateOf("")}

    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        label = { Text("Age of Toy")},
        modifier = Modifier
            .padding(10.dp)
            .wrapContentHeight()
    )
}

@Composable
fun postToy()
{
    universalButton20sp(
        enabled = true,
        text = "Post Toy",
        onClick = { /*TODO*/ },
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .height(100.dp)
            .wrapContentHeight(),
    )
}