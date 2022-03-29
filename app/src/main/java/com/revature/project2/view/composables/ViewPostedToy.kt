package com.revature.project2.view.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


//@Composable
//fun postedToyImage(){
//    Image(modifier = Modifier
//        .padding(10.dp)
//        .fillMaxWidth()
//        .height(100.dp)
//        .wrapContentHeight(),
//
//        shape= CircleShape,
//        onClick = )

@Composable
fun screenTitlePostedToy() {
    Text(
        "Toy Name",
        fontSize = 40.sp,
        modifier = Modifier.padding(10.dp).wrapContentHeight(),
        textAlign = TextAlign.Center
    )
}
@Composable
fun postedToyDescription() {
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
fun seeTradeRequestsOnItem() {
    Button(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()
        .height(100.dp)
        .wrapContentHeight(),
        shape = RoundedCornerShape(25),
        onClick = { /*TODO*/ })


    {
        Text(
            text = "Trade Offers",
            fontSize = 15.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun removePost() {
    Button(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()
        .height(100.dp)
        .wrapContentHeight(),
        shape = RoundedCornerShape(25),
        onClick = { /*TODO*/ })


    {
        Text(
            text = "Remove Post",
            fontSize = 15.sp,
            textAlign = TextAlign.Center
        )
    }
}

