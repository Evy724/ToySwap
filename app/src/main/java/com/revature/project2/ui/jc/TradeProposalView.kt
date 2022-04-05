package com.revature.project2.ui.jc


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*

import androidx.compose.runtime.Composable
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
import com.revature.project2.view.composables.Header
import com.revature.project2.view.composables.ToyCard
import com.revature.project2.view.composables.ToyCardWithButton
import com.revature.project2.view.nav.NavScreens
import com.revature.project2.viewmodel.AllToysViewModel
import com.revature.project2.viewmodel.ToyItemViewModel
import com.revature.project2.viewmodel.TradeViewModel
import com.revature.project2.viewmodel.UserToysViewModel

@Composable
fun tradeProposalScreen(navController: NavController, userToysViewModel: UserToysViewModel)
{
    val context = LocalContext.current
    val viewModel=  ViewModelProvider(context as MainActivity).get(TradeViewModel::class.java)
    val viewVM = ViewModelProvider(context as MainActivity).get(ToyItemViewModel::class.java)
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top)
    {
        Header(text = "Trade Proposal")
    }
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Bottom) {

        Spacer(modifier = Modifier.height(20.dp))
        Card(modifier = Modifier.width(250.dp), border = BorderStroke(1.dp, Color.Black),elevation = 10.dp) {
            Text(text = "Average response time: ", textAlign= TextAlign.Center)
        }
        Spacer(modifier = Modifier.height(20.dp))
        Image(painter = rememberCoilPainter(request = viewVM.toy!!.sImagePath), contentDescription = "",
            modifier = Modifier
                .width(150.dp)
                .height(150.dp)
                .border(width = 1.dp, color = Color.Black))
        Spacer(modifier = Modifier.height(50.dp))
        Row(horizontalArrangement = Arrangement.Center) {
            Image(painter = painterResource(id = R.drawable._4603), contentDescription = "",modifier = Modifier.size(70.dp))
            Spacer(modifier = Modifier.width(100.dp))
            Image(painter = painterResource(id = R.drawable._4603reverse), contentDescription = "", modifier = Modifier.size(70.dp))

        }
        Spacer(modifier = Modifier.height(20.dp))
        Card(modifier = Modifier.fillMaxWidth(), border = BorderStroke(1.dp, Color.Black), elevation = 10.dp) {
            Text(text = "MY TOYS ", textAlign= TextAlign.Center)
        }
        val lazyState = rememberLazyListState()
        val toyList = userToysViewModel.userToys

        LazyColumn(
            state = lazyState,
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            itemsIndexed(toyList) { _, item ->
                ToyCard(toy = item){
                    viewModel.theirToy=item
                    navController
                        .navigate(
                            NavScreens.FinalizeTradeScreen
                                .route
                        )
                }
            }
        }
        BottomBar(navController = navController)
    }
}

@Composable
@Preview
fun preview() {
    Project2Theme {
        val context= LocalContext.current
        val navController=NavController(context)
        tradeProposalScreen(navController = navController,UserToysViewModel())
    }
}
