package com.revature.project2.view.composables

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.revature.project2.view.nav.NavScreens

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
fun newToyPostScreen(navController: NavController){
    Scaffold(bottomBar = {BottomBar(navController)},
            topBar = {Header(text = "New Toy")}) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {

            //screenTitle()
            inputToyName()
            toyDescription()
            //ageOfToy()
            postToy(navController)
        }
    }

}
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
fun postToy(navController: NavController)
{
    val context = LocalContext.current
    universalButton20sp(
        enabled = true,
        text = "Post Toy",
        onClick = {
            Toast.makeText(context,"Toy has been Posted!",Toast.LENGTH_LONG).show()
            navController.popBackStack(
                NavScreens.BrowseItemsScreen.route,false
            ) },
    )
}