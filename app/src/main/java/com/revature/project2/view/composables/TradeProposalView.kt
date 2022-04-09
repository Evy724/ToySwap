package com.revature.project2.view.composables


import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.google.accompanist.coil.rememberCoilPainter
import com.revature.project2.MainActivity

import com.revature.project2.ui.theme.Project2Theme
import com.revature.project2.ui.theme.Purple200
import com.revature.project2.ui.theme.PurpleVariant
import com.revature.project2.ui.theme.Teal200
import com.revature.project2.view.nav.NavScreens
import com.revature.project2.viewmodel.ToyItemViewModel
import com.revature.project2.viewmodel.TradeViewModel
import com.revature.project2.viewmodel.UserToysViewModel

@Composable
fun tradeProposalScreen(navController: NavController)
{
    val context = LocalContext.current
    val viewModel=  ViewModelProvider(context as MainActivity).get(TradeViewModel::class.java)
    val viewVM = ViewModelProvider(context as MainActivity).get(ToyItemViewModel::class.java)
    val userToysViewModel = ViewModelProvider(context as MainActivity).get(UserToysViewModel::class.java)

    Scaffold(topBar = { Header(text = "Trade Proposal") },
    bottomBar ={ BottomBar(navController = navController)},
        modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        )
        {
            Spacer(modifier = Modifier.size(10.dp))
            Card(modifier = Modifier.fillMaxSize(0.90F), elevation = 20.dp,shape= RoundedCornerShape(25.dp)) {
                Column() {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Image(
                        painter = rememberCoilPainter(request = viewVM.toy!!.sImagePath),
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxWidth()

                    )

                }
                Spacer(modifier = Modifier.size(10.dp))

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(25.dp),
                    backgroundColor = Purple200,
                    elevation = 10.dp
                ) {
                    Text(
                        text = "MY TOYS ", textAlign = TextAlign.Center,
                        fontWeight = FontWeight.ExtraBold, fontSize = 16.sp, color = Color.White
                    )
                }
                val lazyState = rememberLazyListState()
                val toyList = userToysViewModel.userToys

                Card(modifier = Modifier.fillMaxSize(), backgroundColor = Teal200) {
                    LazyColumn(
                        state = lazyState,
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        itemsIndexed(toyList) { _, item ->
                            ToyCard(toy = item) {
                                viewModel.theirToy = item
                                navController
                                    .navigate(
                                        NavScreens.FinalizeTradeScreen
                                            .route
                                    )
                            }
                        }
                        item() {
                            Spacer(modifier = Modifier.size(50.dp))
                        }
                    }
                }
            }
            }
        }

    }


}

@Composable
@Preview
fun preview() {
    Project2Theme {
        val context= LocalContext.current
        val navController=NavController(context)
        tradeProposalScreen(navController = navController)
    }
}
