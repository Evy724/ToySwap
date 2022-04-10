package com.revature.project2.ui

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.revature.project2.MainActivity
import com.revature.project2.R
import com.revature.project2.model.api.alltoys.ToyItem
import com.revature.project2.model.api.tradeoffers.TradeOffer
import com.revature.project2.ui.theme.BluishGreen
import com.revature.project2.ui.theme.PurpleVariant
import com.revature.project2.view.composables.*
import com.revature.project2.viewmodel.ToyItemViewModel
import com.revature.project2.viewmodel.TradeOffersViewModel
import com.revature.project2.viewmodel.UserToysViewModel

@Composable
fun AcceptTradeScreen(navController: NavController)
{
    val scaffoldState = rememberScaffoldState()
    val context = LocalContext.current
    val viewTradeVM = ViewModelProvider(context as MainActivity).get(TradeOffersViewModel::class.java)
    val userToysViewModel = ViewModelProvider(context).get(UserToysViewModel::class.java)

    Scaffold(//scaffoldState = scaffoldState,
        content =
        {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                PurpleVariant,
                                BluishGreen
                            )
                        )
                    )
            )
            {
                Header(text = "View Trade Offers")
                ViewTradeOffersBody(navController = navController, userToysViewModel.userTradeOffers)
            }
        },
        bottomBar =
        {
            BottomBar(navController)
        }
    )
}

@Composable
fun ViewTradeOffersBody(navController: NavController, offerList:List<TradeOffer>)
{
    val context = LocalContext.current
    val lazyState = rememberLazyListState()


    if (offerList.isNotEmpty())
    {
        Log.d("View trade offers", "Trade offers loaded")

        Surface(
            modifier = Modifier
                .border(
                    width = 2.dp,
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            PurpleVariant,
                            BluishGreen
                        )
                    ),
                    shape = AbsoluteRoundedCornerShape(topLeft = 10.dp, topRight = 10.dp)
                )
                .clip(
                    shape = RoundedCornerShape(10.dp)
                )
        )
        {
            Surface()
            {
                Image(
                    painter = painterResource(id = R.drawable.minimal_blue_toy_background),
                    contentDescription = "he",
                    modifier = Modifier
                        .height(1000.dp)
                        .width(700.dp),
                    alpha = 0.2F,
                    contentScale = ContentScale.FillBounds
                )
                LazyColumn(

                    state = lazyState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 50.dp)
                        .background(
                            color = Color.Transparent
//                            brush = Brush.horizontalGradient(
//                                colors = listOf(
//                                    PastelPurple,
//                                    Teal200
//                                )
//                            )
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                )
                {

                    items(offerList)
                    { tradeOffer ->
                        Log.d("View trade offers", "Lazy column call")
                        AcceptOrDeclineTradeOffersToyCard(
                            offer = tradeOffer, navController = navController
                        )
                    }
                }
            }
        }
    }
}

//    Scaffold(
//        scaffoldState = scaffoldState,
//        content =
//        {
//            Header(text = "Accept Trade")
//            Column(
//                horizontalAlignment = Alignment.CenterHorizontally,
//                verticalArrangement = Arrangement.Top,
//                modifier = Modifier
//                    .fillMaxSize()
//                    .background(
//                        MaterialTheme.colors.surface,
//                        RectangleShape
//                    )
//            )
//            {
//                Spacer(modifier = Modifier.height(20.dp))
//
//                Text(
//                    text = "Somebody wants to\ntrade with you!",
//                    fontSize = 25.sp,
//                    textAlign = TextAlign.Center
//                )
//
//                Spacer(modifier = Modifier.height(15.dp))
//
//                Text(
//                    text = "Proposed Trade:",
//                    fontSize = 25.sp,
//                    textAlign = TextAlign.Center
//                )
//
//                Spacer(modifier = Modifier.height(10.dp))
//
//                Row(
//                    modifier =
//                    Modifier.padding(5.dp),
//                    verticalAlignment = Alignment.Top,
//                    horizontalArrangement = Arrangement.Center,
//                )
//                {
//
//                    // Left column
//                    Column(
//                        modifier = Modifier.padding(5.dp),
//                        horizontalAlignment = Alignment.CenterHorizontally
//                    )
//                    {
//
//                        Spacer(modifier = Modifier.height(40.dp))
//                        // Down arrow
//                        Image(
//                            painter = painterResource(id = R.drawable.down_arrow),
//                            contentDescription = "Down arrow",
//                            modifier = Modifier.size(70.dp),
//                            alignment = Alignment.BottomCenter
//                        )
//
//                        Spacer(modifier = Modifier.height(100.dp))
//
//                        // What you are trading box
//                        Surface(
//                            modifier = Modifier
//                                .height(70.dp)
//                                .width(100.dp),
//                            border = BorderStroke(
//                                width = 2.dp,
//                                color = Color.White
//                            )
//                        )
//                        {
//                            Text(
//                                text = "What you \nare trading\n->",
//                                textAlign = TextAlign.Center,
//                                fontSize = 17.sp
//                            )
//                        }
//
//
//                        Spacer(modifier = Modifier.height(64.dp))
//                    }
//
//                    // Middle column
//                    Column(
//                        modifier = Modifier.padding(5.dp),
//                        horizontalAlignment = Alignment.CenterHorizontally
//                    )
//                    {
//
//                        // Toy pic for what the other user is trading
//                        Surface()
//                        {
//                            Image(
//                                painter = painterResource(id = R.drawable.pokemon_legends_arceus_nintendo_switch_in_box),
//                                contentDescription = "Toy pic of Pokemon Legends Arceus from other user",
//                                modifier = Modifier.size(150.dp),
//                                alignment = Alignment.TopCenter
//                            )
//                        }
//
//                        // Toy pic for what you are trading
//                        Surface()
//                        {
//                            Image(
//                                painter = painterResource(R.drawable.sleeping_waddle_dee_plushie),
//                                contentDescription = "Toy pic of the sleeping Waddle Dee plushie from you",
//                                modifier = Modifier.size(150.dp),
//                                alignment = Alignment.BottomCenter
//                            )
//                        }
//                    }
//
//                    // Right column
//                    Column(
//                        modifier = Modifier.padding(5.dp),
//                        horizontalAlignment = Alignment.CenterHorizontally
//                    )
//                    {
//                        Spacer(modifier = Modifier.height(40.dp))
//
//                        // What the other user is trading box
//                        Surface(
//                            modifier = Modifier
//                                .height(100.dp)
//                                .width(100.dp),
//                            border = BorderStroke(
//                                width = 2.dp,
//                                color = Color.White
//                            )
//                        )
//                        {
//                            Text(
//                                text = "What the \nother user \nis trading \n<-",
//                                textAlign = TextAlign.Center,
//                                fontSize = 17.sp
//                            )
//                        }
//
//                        Spacer(modifier = Modifier.height(70.dp))
//
//                        // Up arrow
//                        Image(
//                            painter = painterResource(id = R.drawable.up_arrow),
//                            contentDescription = "Up arrow",
//                            modifier = Modifier.size(70.dp),
//                            alignment = Alignment.TopCenter
//                        )
//                    }
//                }
//
//                Text(
//                    text = "Will you accept this trade?",
//                    fontSize = 25.sp,
//                    textAlign = TextAlign.Center
//                )
//
//                Row(
//                    modifier = Modifier.padding(5.dp),
//                    verticalAlignment = Alignment.CenterVertically,
//                    horizontalArrangement = Arrangement.Center,
//                )
//                {
//                    // Left accept button
//                    Column(
//                        modifier = Modifier.padding(16.dp),
//                        horizontalAlignment = Alignment.CenterHorizontally)
//                    {
//                        universalButton20sp(
//                            enabled = true,
//                            text = "Accept",
//                            onClick = {
//
//                                //Accept trade API call
//
//
//                                Toast.makeText(context,"Trade Accepted",Toast.LENGTH_LONG).show()
//                                navController.popBackStack(NavScreens.BrowseItemsScreen.route,false)
//
//                            },
//                        )
//                    }
//
//                    // Right decline button
//                    Column(
//                        modifier = Modifier.padding(16.dp),
//                        horizontalAlignment = Alignment.CenterHorizontally
//                    )
//                    {
//                        universalButton20sp(
//                            enabled = true,
//                            text = "Decline",
//                            onClick = {
//
//                                //Decline Trade Api call
//
//
//                                Toast.makeText(context,"Trade Rejected",Toast.LENGTH_LONG).show()
//                                navController.popBackStack(NavScreens.BrowseItemsScreen.route,false)
//
//
//                            },
//                        )
//                    }
//                }
//            }
//        },
//        bottomBar = {BottomBar(navController = navController)}
//    )


//@Preview
//@Composable
//fun PreviewAcceptTradeScreen()
//{
//    AcceptTradeScreen(navController = NavController(context = Context))
//}

