package com.revature.project2.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.google.accompanist.coil.rememberCoilPainter
import com.revature.project2.MainActivity
import com.revature.project2.ui.theme.Purple200
import com.revature.project2.ui.theme.PurpleVariant

import com.revature.project2.view.composables.BottomBar
import com.revature.project2.view.composables.Header
import com.revature.project2.view.composables.universalButton20sp
import com.revature.project2.view.nav.NavScreens
import com.revature.project2.viewmodel.ToyItemViewModel

@Composable
fun ViewItemScreen(
    navController: NavController,

)
{
    val context = LocalContext.current
    val viewVM = ViewModelProvider(context as MainActivity).get(ToyItemViewModel::class.java)
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        content =
        {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .background(
                        MaterialTheme.colors.surface,
                        RectangleShape
                    )
                    .fillMaxWidth()
            )
            {
                Header(text = viewVM.toy!!.sName)

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    viewVM.toy!!.sName,
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Toy image
                Image(
                    painter = rememberCoilPainter(request = viewVM.toy!!.sImagePath,),
                    contentDescription = null,
                    Modifier.size(250.dp)
                        .clip(shape = RoundedCornerShape(10.dp))
                        .border(
                            width = 4.dp,
                            brush = Brush.horizontalGradient(
                                colors = listOf(
                                    Purple200,
                                    PurpleVariant
                                )
                            ),
                            shape = RoundedCornerShape(10.dp)
                        )
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
                universalButton20sp(
                    enabled = true,
                    text = "Request Trade",
                    onClick =
                    {
                        navController.navigate(NavScreens.TradeProposalScreen.route)
                    },
                )
            }
        },
        bottomBar = {BottomBar(navController = navController)}
    )
}

