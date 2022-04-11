package com.revature.project2.ui

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
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
import com.revature.project2.ui.theme.*

import com.revature.project2.view.composables.BottomBar
import com.revature.project2.view.composables.Header
import com.revature.project2.view.composables.universalButton20sp
import com.revature.project2.view.nav.NavScreens
import com.revature.project2.view.nav.StartNav
import com.revature.project2.viewmodel.ToyItemViewModel

@Composable
fun ViewItemScreen(
    navController: NavController,

    ) {
    val context = LocalContext.current
    val viewVM = ViewModelProvider(context as MainActivity).get(ToyItemViewModel::class.java)
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { Header(text = viewVM.toy!!.sName) },

        bottomBar = { BottomBar(navController = navController) }
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
//                .background(
//                    MaterialTheme.colors.surface,
//                    RectangleShape
//                )
                .background(color = TealGreen )
                .fillMaxSize()
                .verticalScroll(ScrollState(1))
        )
        {

            // Toy image
            Box(modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.TopCenter) {
                Column() {
//                        Text(
//                            viewVM.toy!!.sName,
//                            fontSize = 30.sp,
//                            textAlign = TextAlign.Center
//                        )


                    Image(
                        painter = rememberCoilPainter(request = viewVM.toy!!.sImagePath),
                        contentDescription = null,
                        Modifier
                            .fillMaxWidth()
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
                }

            }
            Spacer(modifier = Modifier.size(35.dp))
            Card(elevation = 10.dp, modifier = Modifier.fillMaxSize(), shape = RoundedCornerShape(20.dp)) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Spacer(modifier = Modifier.size(25.dp))
                    Text(
                        text = viewVM.toy!!.sDescription,
                        fontSize = 17.sp,
                        color = Purple500,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 15.dp),
                        fontWeight = FontWeight.Medium

                    )

                    Spacer(modifier = Modifier.height(25.dp))
                    // To trade proposal screen
                    universalButton20sp(
                        enabled = true,
                        text = "Request Trade",
                        onClick =
                        {
                            navController.navigate(NavScreens.TradeProposalScreen.route)
                        },
                    )
                    Spacer(modifier = Modifier.size(25.dp))
                }
            }


        }


    }
}
