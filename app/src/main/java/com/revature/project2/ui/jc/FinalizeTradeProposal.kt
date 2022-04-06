package com.revature.project2.ui.jc


import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.google.accompanist.coil.rememberCoilPainter
import com.revature.project2.MainActivity
import com.revature.project2.R
import com.revature.project2.ui.theme.Project2Theme
import com.revature.project2.view.composables.BottomBar
import com.revature.project2.view.composables.Header
import com.revature.project2.view.composables.universalButton20sp
import com.revature.project2.view.nav.NavScreens
import com.revature.project2.viewmodel.ToyItemViewModel
import com.revature.project2.viewmodel.TradeViewModel

@Composable
fun tradeFinalizeScreen(navController: NavController)
{
    val context= LocalContext.current
    val theirviewModel=  ViewModelProvider(context as MainActivity).get(TradeViewModel::class.java)
    val myviewModel=  ViewModelProvider(context as MainActivity).get(ToyItemViewModel::class.java)
    var message by rememberSaveable{ mutableStateOf("")}


    var finalMessage=""
//    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top)

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top)
    {

        Header(text = "Finalize Trade")

        Spacer(modifier = Modifier.height(20.dp))

        Image(
            painter = rememberCoilPainter(request = theirviewModel.theirToy!!.sImagePath), contentDescription = "",
            modifier = Modifier
                .size(100.dp)
                .border(width = 1.dp, color = Color.Black)
        )
        Spacer(modifier = Modifier.height(50.dp))
        Row(horizontalArrangement = Arrangement.Center)
        {
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
                .size(100.dp)
                .border(width = 1.dp, color = Color.Black)
        )
        Spacer(modifier = Modifier.height(50.dp))
        Card(modifier = Modifier.fillMaxWidth(), border = BorderStroke(1.dp, Color.Black)) {

            TextField(
                value = message,
                onValueChange =
                { message = it },
                placeholder = { Text(text = "Send message to user here") })
        }

        Spacer(modifier = Modifier.height(20.dp))
        universalButton20sp(
            enabled = true,
            text = "Send",
            onClick =
            {
                finalMessage=message
                theirviewModel.getSendTrade_msg("TO-${myviewModel.toy!!.id}-${theirviewModel.theirToy!!.id}",finalMessage)
                Toast.makeText(context, "Proposal Request Sent",Toast.LENGTH_LONG).show()


                navController.popBackStack(NavScreens.BrowseItemsScreen.route,false)
            }
        )
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

