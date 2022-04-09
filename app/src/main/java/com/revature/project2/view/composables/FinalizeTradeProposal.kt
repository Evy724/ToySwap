package com.revature.project2.ui.jc


import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.google.accompanist.coil.rememberCoilPainter
import com.revature.project2.MainActivity
import com.revature.project2.R
import com.revature.project2.ui.theme.*
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

   Scaffold(bottomBar = {BottomBar(navController = navController)},
       topBar = {Header(text = "Finalize Trade")}) {
       Column(
           modifier = Modifier
               .fillMaxSize()
               .verticalScroll(ScrollState(0)),
           horizontalAlignment = Alignment.CenterHorizontally,
           verticalArrangement = Arrangement.Top
           )
       {
           Spacer(modifier = Modifier.size(10.dp))
           Card(modifier = Modifier.fillMaxSize(0.90F), elevation = 20.dp,shape= RoundedCornerShape(25.dp))
           {
               Column() {

                   Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
                       Column() {
                           Spacer(modifier = Modifier.size(10.dp))
                           Image(
                               painter = rememberCoilPainter(request = theirviewModel.theirToy!!.sImagePath), contentDescription = "",
                               modifier = Modifier
                                   .size(300.dp)
                                   .clip(shape= RoundedCornerShape(15.dp))


                           )
                           Spacer(modifier = Modifier.size(10.dp))
                           Image(
                               painter = rememberCoilPainter(request = myviewModel.toy!!.sImagePath), contentDescription = "",
                               modifier = Modifier
                                   .size(300.dp)
                                   .clip(shape= RoundedCornerShape(15.dp))



                           )
                       }
                   }

                   Card(backgroundColor = Teal200 , modifier = Modifier.fillMaxSize(),elevation = 100.dp) {
                       Column(horizontalAlignment = Alignment.CenterHorizontally) {
                           Spacer(modifier = Modifier.height(5.dp))
                           Card(modifier = Modifier.fillMaxWidth().height(24.dp),
                               elevation = 10.dp, backgroundColor = Purple200, shape = RoundedCornerShape(25.dp)) {
                               Text(text = "Message ", textAlign= TextAlign.Center, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.White)
                           }
                           Spacer(modifier = Modifier.height(10.dp))
                           Card(modifier = Modifier.fillMaxWidth().padding(5.dp)) {

                               TextField(
                                   value = message,
                                   onValueChange =
                                   { message = it },
                                   placeholder = { Text(text = "Send message to user here") })
                           }

                           Spacer(modifier = Modifier.height(10.dp))
                           universalButton20sp(
                               enabled = true,
                               text = "Send",
                               modifier = Modifier
                                   .background(
                                       color = Purple200,
                                       shape = RoundedCornerShape(5.dp)
                                       ),
                               onClick =
                               {

                                   finalMessage=message
                                   theirviewModel.getSendTrade_msg("TO-${myviewModel.toy!!.id}-${theirviewModel.theirToy!!.id}",finalMessage)
                                   Toast.makeText(context, "Proposal Request Sent",Toast.LENGTH_LONG).show()


                                   navController.popBackStack(NavScreens.BrowseItemsScreen.route,false)
                               }
                           )
                           Spacer(modifier = Modifier.height(30.dp))
                       }
                   }
               }
           }
           Spacer(modifier = Modifier.height(100.dp))

       }
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

