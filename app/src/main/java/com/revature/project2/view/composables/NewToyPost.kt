package com.revature.project2.view.composables

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Absolute.Center
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.revature.project2.R
import com.revature.project2.ui.theme.BluishGreen
import com.revature.project2.ui.theme.PurpleVariant
import com.revature.project2.view.nav.NavScreens
import com.revature.project2.viewmodel.AllToysViewModel
import java.time.format.TextStyle

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
fun newToyPostScreen(navController: NavController) {
    Scaffold(bottomBar = { BottomBar(navController) })
    {
        Surface()
        {
            Image(
                painter = painterResource(id = R.drawable.minimal_blue_toy_background),
                contentDescription = "he",
                modifier = Modifier
                    .height(1000.dp)
                    .width(700.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.FillBounds
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
//                    .background(
//
//                        brush = Brush.horizontalGradient(
//                            colors = listOf(
//                                PurpleVariant,
//                                BluishGreen
//                            )
//                        )
//                    )
            )
            {

                Header(text = "Browse Toys")
                inputToyName()
                toyDescription()
                ageOfToy()
                postToy(navController)
            }
        }
    }

}
@Composable
fun screenTitle() {
    Text("New Toy Post", fontSize = 40.sp,color = Color.White, modifier = Modifier
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
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .height(100.dp)
            .wrapContentHeight(),
    )
}