package com.revature.project2.ui.jc


import android.widget.Toast
import androidx.activity.viewModels
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.google.accompanist.coil.rememberCoilPainter
import com.revature.project2.MainActivity
import com.revature.project2.R
import com.revature.project2.model.api.alltoys.ToyItem
import com.revature.project2.ui.theme.Project2Theme
import com.revature.project2.view.composables.BottomBar
import com.revature.project2.view.nav.NavScreens
import com.revature.project2.viewmodel.ToyItemViewModel
import com.revature.project2.viewmodel.TradeViewModel
import com.revature.project2.viewmodel.UserToysViewModel
import com.revature.project2.viewmodel.msgFromTVM

@Composable
fun tradeFinalizeScreen(navController: NavController)
{
    val context= LocalContext.current
    val theirviewModel=  ViewModelProvider(context as MainActivity).get(TradeViewModel::class.java)
    val myviewModel=  ViewModelProvider(context as MainActivity).get(ToyItemViewModel::class.java)
    var message by rememberSaveable{ mutableStateOf("")}


    var finalMessage=""
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top) {
        TopAppBar() {
            Text(text = "Finalize Trade")
        }
    }

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Bottom) {

            Spacer(modifier = Modifier.height(20.dp))
            Image(
                painter = rememberCoilPainter(request = theirviewModel.theirToy!!.sImagePath), contentDescription = "",
                modifier = Modifier
                    .width(150.dp)
                    .height(150.dp)
                    .border(width = 1.dp, color = Color.Black)
            )
            Spacer(modifier = Modifier.height(50.dp))
            Row(horizontalArrangement = Arrangement.Center) {
                Image(
                    painter = painterResource(id = R.drawable._4603),
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
            Spacer(modifier = Modifier.height(50.dp))
            Image(
                painter = rememberCoilPainter(request = myviewModel.toy!!.sImagePath), contentDescription = "",
                modifier = Modifier
                    .width(150.dp)
                    .height(150.dp)
                    .border(width = 1.dp, color = Color.Black)
            )
            Spacer(modifier = Modifier.height(50.dp))
            Card(modifier = Modifier.fillMaxWidth(), border = BorderStroke(1.dp, Color.Black)) {

                TextField(value = message, onValueChange = {
                    message = it
                }, placeholder = { Text(text = "Send message to user here") })
            }


            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = {
                finalMessage=message
                theirviewModel.getSendTrade_msg("TO-${myviewModel.toy!!.id}-${theirviewModel.theirToy!!.id}",finalMessage)
                Toast.makeText(context, "Proposal Request Sent",Toast.LENGTH_LONG).show()


                navController.popBackStack(NavScreens.BrowseItemsScreen.route,false)
            }) {
                Text(text = "Send")
            }
        Spacer(modifier = Modifier.height(50.dp))

        BottomBar(navController = navController)

        }



    }





@Composable
@Preview
fun previewFT() {
    Project2Theme {
        // A surface container using the 'background' color from the theme
        val context= LocalContext.current
        val navController=NavController(context)
        tradeFinalizeScreen(navController = navController)
    }
}
