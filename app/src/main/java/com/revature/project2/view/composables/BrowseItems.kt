package com.revature.project2.view.composables

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.revature.project2.MainActivity
import com.revature.project2.R
import com.revature.project2.model.AppManager
import com.revature.project2.model.api.alltoys.ToyItem
import com.revature.project2.ui.theme.*
import com.revature.project2.view.nav.NavScreens
import com.revature.project2.viewmodel.AllToysViewModel
import com.revature.project2.viewmodel.LoginViewModel
import com.revature.project2.viewmodel.ToyItemViewModel

/**
 * Browse Items screen using scaffold
 *
 * Uses ToyCard and BottomBar from shared file
 */
@Composable
fun BrowseItemsScreen(navController: NavController)
{

    val context = LocalContext.current
    val browseViewModel = ViewModelProvider(context as MainActivity).get(AllToysViewModel::class.java)

    Log.d("Browse Screen", "Browse Screen Start")

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
                Header(text = "Browse Toys")
                BrowseItemsBody(navController, browseViewModel.allToys )
            }
        },
        bottomBar =
        {
            BottomBar(navController)
        }
    )
}
@Composable
fun BrowseItemsBody(navController: NavController, toyList:List<ToyItem>)
{
    val context = LocalContext.current
    val lazyState = rememberLazyListState()

    if (toyList.isNotEmpty())
    {
        Log.d("Browse Screen","Toys Loaded")

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
                    alpha=0.2F,
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
                        )
                    ,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                )
                {

                    items(toyList){ toy->
                        Log.d("Browse Screen","Lazy Column call")
                        if(toy.posterId != AppManager.currentUser.nUserId) {
                            ToyCard(toy = toy) {
                                val viewVM =
                                    ViewModelProvider(context as MainActivity).get(ToyItemViewModel::class.java)
                                viewVM.toy = toy
                                navController.navigate(NavScreens.ViewItemScreen.route)
                            }
                        }
                    }
                }
            }
//            LazyColumn(
//
//                state = lazyState,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(bottom = 50.dp)
//                    .background(
//                        brush = Brush.horizontalGradient(
//                            colors = listOf(
//                                PastelPurple,
//                                Teal200
//                            )
//                        )
//                    )
//                ,
//                horizontalAlignment = Alignment.CenterHorizontally)
//            {
//
//                items(toyList){ toy->
//                    Log.d("Browse Screen","Lazy Column call")
//                    ToyCard(toy = toy){
//                        val viewVM = ViewModelProvider(context as MainActivity).get(ToyItemViewModel::class.java)
//                        viewVM.toy = toy
//                        navController.navigate(NavScreens.ViewItemScreen.route)
//                    }
//                }
//            }
        }
    }
    else
    {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {
            CircularProgressIndicator()
            Log.d("Browse Screen","Loading Screen")

        }
    }
}