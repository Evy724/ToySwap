package com.revature.project2.view.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.revature.project2.R
import com.revature.project2.view.nav.NavScreens

@Composable
fun viewPostedToyScreen(navController: NavController) {

    Scaffold(bottomBar = { BottomBar(navController) }) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {

            postedToyImage()
            screenTitlePostedToy()
            postedToyDescription()

            //Temp if, should check if trades are available for this toy
            if(true) {
                seeTradeRequestsOnItem(navController)
            }
            removePost(navController)

        }
    }
}

@Composable
fun screenTitlePostedToy() {
    Text(
        "Toy Name",
        fontSize = 40.sp,
        modifier = Modifier
            .padding(10.dp)
            .wrapContentHeight(),
        textAlign = TextAlign.Center
    )
}
@Composable
fun postedToyImage() {
    Image(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .height(100.dp)
            .wrapContentHeight(),
        painter = painterResource(
            id = R.drawable.pokemon_legends_arceus_nintendo_switch_in_box),
        contentDescription = null)
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
fun seeTradeRequestsOnItem(navController: NavController) {
    Button(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()
        .height(100.dp)
        .wrapContentHeight(),
        shape = RoundedCornerShape(25),
        onClick = {

            //Send toy to trade request screen and navigate
            navController.navigate(NavScreens.AcceptTradeScreen.route)
        })
    {
        Text(
            text = "Trade Offers",
            fontSize = 15.sp,
            textAlign = TextAlign.Center
        )
    }
}
    @Composable
fun seeTradeRequestsOnItem()
{
    universalButton20sp(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .height(100.dp)
            .wrapContentHeight(),
        enabled = true,
        text ="Trade Offers",
        onClick = { /*TODO*/ }
    )
}

@Composable
fun removePost(navController: NavController) {

    val context = LocalContext.current
    universalButton20sp(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()
        .height(100.dp)
        .wrapContentHeight(),
        text = "Remove Post",
        enabled = true,
        onClick = {
            //Code to remove toy from api here


            Toast.makeText(context,"Toy Removed!",Toast.LENGTH_LONG).show()
            navController.popBackStack(
                NavScreens.BrowseItemsScreen.route,
                false
            )

        })
}

