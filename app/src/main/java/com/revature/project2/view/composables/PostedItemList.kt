package com.revature.project2.view.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.revature.project2.MainActivity
import com.revature.project2.R
import com.revature.project2.ui.theme.BluishGreen
import com.revature.project2.ui.theme.PurpleVariant
import com.revature.project2.view.nav.NavScreens
import com.revature.project2.viewmodel.AllToysViewModel
import com.revature.project2.viewmodel.ToyItemViewModel
import com.revature.project2.viewmodel.UserToysViewModel

@Composable
fun PostedItemsScreen(navController: NavController)
{

    val scaffoldState = rememberScaffoldState()
    val context = LocalContext.current
    val userToysViewModel =
        ViewModelProvider(context as MainActivity).get(UserToysViewModel::class.java)

    userToysViewModel.getUserOffers()

    val itemViewModel =
        ViewModelProvider(context).get(ToyItemViewModel::class.java)

    val offerlist = userToysViewModel.userTradeOffers

    Scaffold(scaffoldState = scaffoldState,
        floatingActionButton =
        {
            FloatingActionButton(
                onClick = { navController.navigate(NavScreens.NewToyPostScreen.route)
                })
            {
                Icon(Icons.Filled.Add,"")
            }},

//        floatingActionButton =
//        {
//            FloatingActionButton(onClick =
//            {
//
//            }
//            )
//            {
//                Icon(Icons.Filled.Add,"")
//            }
//        },
        content =
        {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                PurpleVariant,
                                BluishGreen
                            )
                        )
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
            )
            {
                Header(text = "Posted Toys")

                if (userToysViewModel.userToys.isNotEmpty())
                {
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
                                shape = AbsoluteRoundedCornerShape(
                                    topLeft = 10.dp,
                                    topRight = 10.dp
                                )
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
                                contentScale = ContentScale.FillBounds
                            )
                            val lazyState = rememberLazyListState()
                            val toyList = userToysViewModel.userToys

                            LazyColumn(
                                state = lazyState,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 50.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            )
                            {

                        itemsIndexed(toyList) { _, item ->

                            var bNotice = false
                            offerlist.forEach {
                                if (item.id == it.nUserToyId)
                                    bNotice = true
                            }

                            ToyCard(toy = item, bNotification = bNotice){
                                itemViewModel.toy = item
                                        navController.navigate((NavScreens.ViewPostedToyScreen.route))
                                //navController.navigate(NavScreens.ViewItemScreen.route)
                            }
                        }
                    }
                        }
                    }
                }
                else
                {

                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text("Loading", style = MaterialTheme.typography.h3)
                    }
                }



//
//                Spacer(Modifier.size(40.dp))
//                Button(modifier =
//                Modifier
//                    .padding(5.dp)
//                    .fillMaxWidth(.5f),
//                    onClick = {
//
//                    }) {
//                    Text(text = "Post Toy")
//
//                }
                
                
            }
        },
        bottomBar = {BottomBar(navController = navController)}
    )
}