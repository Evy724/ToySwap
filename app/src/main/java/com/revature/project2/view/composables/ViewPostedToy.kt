package com.revature.project2.view.composables

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.google.accompanist.coil.rememberCoilPainter
import com.revature.project2.MainActivity
import com.revature.project2.R
import com.revature.project2.ui.theme.Purple200
import com.revature.project2.ui.theme.Teal200
import com.revature.project2.ui.theme.TealGreen
import com.revature.project2.view.nav.NavScreens
import com.revature.project2.viewmodel.ToyItemViewModel
import com.revature.project2.viewmodel.UserToysViewModel

@Composable
fun viewPostedToyScreen(navController: NavController)
{
    val context = LocalContext.current
    val userToysViewModel = ViewModelProvider(context as MainActivity).get(UserToysViewModel::class.java)


    Scaffold(bottomBar = { BottomBar(navController) }, topBar = {Header(text = userToysViewModel.toy?.sName?:"My Toy")})
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(
                            TealGreen,
                            Teal200
                        )
                    )
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {

            Card(modifier = Modifier.fillMaxSize(),
                shape = RoundedCornerShape(25.dp)) {
                Column(modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally)
                {
                    postedToyImage(userToysViewModel)
                    screenTitlePostedToy(userToysViewModel)
                    postedToyDescription(userToysViewModel)

                    //Temp if, should check if trades are available for this toy
                    if(true)
                    {
                        seeTradeRequestsOnItem(navController,userToysViewModel)
                    }
                    removePost(navController,userToysViewModel)
                }

            }
        }
    }
}

@Composable
fun screenTitlePostedToy(userToysViewModel:UserToysViewModel) {
    Text(
        userToysViewModel.toy?.sName?:"My Toy",
        fontSize = 40.sp,
        modifier = Modifier
            .padding(10.dp)
            .wrapContentHeight(),
        textAlign = TextAlign.Center,
        color = Purple200
    )
}
@Composable
fun postedToyImage(userToysViewModel:UserToysViewModel) {
    Image(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        painter = rememberCoilPainter(request = userToysViewModel.toy!!.sImagePath),
        contentDescription = null)
}
@Composable
fun postedToyDescription(userToysViewModel:UserToysViewModel) {


    Text(
        text= userToysViewModel.toy?.sDescription?:"Toy Description"
    )
}

@Composable
fun seeTradeRequestsOnItem(navController: NavController,userToysViewModel:UserToysViewModel) {
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
fun seeTradeRequestsOnItem(userToysViewModel:UserToysViewModel)
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
fun removePost(navController: NavController,userToysViewModel:UserToysViewModel) {

    val context = LocalContext.current
    universalButton20sp(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()
        .height(100.dp)
        .wrapContentHeight(),
        text = "Remove Post",
        enabled = true,
        onClick = {
            //userToysViewModel.userToys


            Toast.makeText(context,"Toy Removed!",Toast.LENGTH_LONG).show()
            navController.popBackStack(
                NavScreens.BrowseItemsScreen.route,
                false
            )

        })
}

