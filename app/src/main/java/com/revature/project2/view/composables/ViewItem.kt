package com.revature.project2.ui

import android.view.View
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.google.accompanist.coil.rememberCoilPainter
import com.revature.project2.MainActivity
import com.revature.project2.R
import com.revature.project2.model.api.alltoys.ToyItem
import com.revature.project2.view.composables.BottomBar
import com.revature.project2.view.nav.NavScreens
import com.revature.project2.viewmodel.ToyItemViewModel
import android.content.Context as Context

@Composable
fun ViewItemScreen(
    navController: NavController,

)
{
    val context = LocalContext.current
    val viewVM = ViewModelProvider(context as MainActivity).get(ToyItemViewModel::class.java)
    val scaffoldState = rememberScaffoldState()
    Scaffold(scaffoldState = scaffoldState, topBar =
    {
        TopAppBar(
            title = { Text(text = "View Item") },
            backgroundColor = MaterialTheme.colors.secondary
        )
    },
        content =
        {
            Column(horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .background(
                        MaterialTheme.colors.surface,
                        RectangleShape
                    )
            )
            {
                Spacer(modifier = Modifier.height(20.dp))

                Text(
//                    text = "My Melody Pop! Vinyl Figure",
                    viewVM.toy!!.sName,
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Toy image
                Image(
//                    painter = painterResource(id = R.drawable.my_melody_funko_pop_in_box),
                    painter = rememberCoilPainter(request = viewVM.toy!!.sImagePath,),
                    contentDescription = null,
                    Modifier.size(250.dp)
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = viewVM.toy!!.sDescription,
                    fontSize = 17.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 15.dp)
                )
                Spacer(modifier = Modifier.height(20.dp))

                // To trade proposal screen
                Button(
                    onClick = {
//                        navController.navigate(NavScreens.TradeProposalScreen.route)
                    }
                )
                {
                    Text(
                        text = "Request Trade",
                        fontSize = 25.sp
                    )
                }
            }
        },
        bottomBar = {BottomBar(navController = navController)}
    )
}

//@Preview
//@Composable
//fun PreviewViewItemScreen()
//{
//    ViewItemScreen(NavController(context = Context))
//}