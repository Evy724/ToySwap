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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.coil.rememberCoilPainter

import com.revature.project2.R
import com.revature.project2.model.api.alltoys.ToyItem
import com.revature.project2.ui.theme.Project2Theme
import com.revature.project2.view.composables.BottomBar
import com.revature.project2.view.composables.ToyCard
import com.revature.project2.view.composables.ToyCardWithButton
import com.revature.project2.view.nav.NavScreens
import com.revature.project2.viewmodel.AllToysViewModel
import com.revature.project2.viewmodel.UserToysViewModel

@Composable
fun tradeProposalScreen(navController: NavController, userToysViewModel: UserToysViewModel,toyid:Int,toyImage:String)
{
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        TopAppBar() {
            Text(text = "Trade Proposal")
        }
        Spacer(modifier = Modifier.height(20.dp))
        Card(modifier = Modifier.width(250.dp), border = BorderStroke(3.dp, Color.Black)) {
            Text(text = "Average response time: ", textAlign= TextAlign.Center)
        }
        Spacer(modifier = Modifier.height(20.dp))
        Image(painter = rememberCoilPainter(request = toyImage), contentDescription = "",
            modifier = Modifier
                .width(150.dp)
                .height(150.dp)
                .border(width = 3.dp, color = Color.Black))
        Spacer(modifier = Modifier.height(50.dp))
        Row(horizontalArrangement = Arrangement.Center) {
            Image(painter = painterResource(id = R.drawable._4603), contentDescription = "",modifier = Modifier.size(70.dp))
            Spacer(modifier = Modifier.width(100.dp))
            Image(painter = painterResource(id = R.drawable._4603reverse), contentDescription = "", modifier = Modifier.size(70.dp))

        }
        Spacer(modifier = Modifier.height(20.dp))
        Card(modifier = Modifier.fillMaxWidth(), border = BorderStroke(3.dp, Color.Black)) {
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
                ToyCardWithButton(toy = item,"Trade",{navController.navigate(NavScreens.FinalizeTradeScreen.route)})
            }
        }
        BottomBar(navController = navController)
    }
}

@Composable
@Preview
fun preview() {
    Project2Theme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
//            val navController=NavController
//            tradeProposalScreen()

        }
    }
}