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
import androidx.compose.ui.draw.clipToBounds
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
import com.revature.project2.ui.theme.*
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
                    .padding(bottom = 50.dp),
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




//@Preview
//@Composable
//fun PreviewAcceptTradeScreen()
//{
//    AcceptTradeScreen(navController = NavController(context = Context))
//}

