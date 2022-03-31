package com.revature.project2.ui.jc


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.coil.rememberCoilPainter
import com.revature.project2.R
import com.revature.project2.model.api.alltoys.ToyItem
import com.revature.project2.ui.theme.Project2Theme
import com.revature.project2.view.composables.BottomBar
import com.revature.project2.view.nav.NavScreens

@Composable
fun tradeFinalizeScreen(navController: NavController, myToyID: Int, theirToyID:Int,myImage:String,theirImage:String)
{
    var message by rememberSaveable{ mutableStateOf("")}
    var finalMessage=""
    Column() {
        TopAppBar() {
            Text(text = "Finalize Trade")
        }
        LazyColumn(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            item {
                Spacer(modifier = Modifier.height(20.dp))
                Image(
                    painter = rememberCoilPainter(request = theirImage), contentDescription = "",
                    modifier = Modifier
                        .width(200.dp)
                        .height(200.dp)
                        .border(width = 3.dp, color = Color.Black)
                )
                Spacer(modifier = Modifier.height(20.dp))
                Row(horizontalArrangement = Arrangement.Center) {
                    Image(
                        painter = rememberCoilPainter(request = myImage),
                        contentDescription = "",
                        modifier = Modifier.size(70.dp)
                    )
                    Spacer(modifier = Modifier.width(100.dp))
                    Image(
                        painter = painterResource(id = R.drawable._4603reverse),
                        contentDescription = "",
                        modifier = Modifier.size(70.dp)
                    )

                }
                Spacer(modifier = Modifier.height(20.dp))
                Image(
                    painter = painterResource(id = R.drawable.lego), contentDescription = "",
                    modifier = Modifier
                        .width(200.dp)
                        .height(200.dp)
                        .border(width = 3.dp, color = Color.Black)
                )
                Spacer(modifier = Modifier.height(20.dp))
                Card(modifier = Modifier.fillMaxWidth(), border = BorderStroke(3.dp, Color.Black)) {

                    TextField(value = message, onValueChange = {
                        message = it
                    }, placeholder = { Text(text = "Send message to user here") })
                }


                Spacer(modifier = Modifier.height(20.dp))
                Button(onClick = {
                    finalMessage=message

                    navController.popBackStack(NavScreens.BrowseItemsScreen.route,false)
                }) {
                    Text(text = "Send")
                }
                Spacer(modifier = Modifier.height(200.dp))

            }

        }
        BottomBar(navController = navController)

    }


}

@Composable
@Preview
fun previewFT() {
    Project2Theme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
//            tradeFinalizeScreen()

        }
    }
}

