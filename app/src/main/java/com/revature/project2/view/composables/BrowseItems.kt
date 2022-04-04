package com.revature.project2.view.composables

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.revature.project2.MainActivity
import com.revature.project2.model.api.alltoys.ToyItem
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
        topBar = { TopAppBar( title = {Text("Browse Items: ")},
            backgroundColor = MaterialTheme.colors.secondary) },
        content =
        {
            BrowseItemsBody(navController, browseViewModel.allToys )
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

        LazyColumn(state = lazyState,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally)
        {

            items(toyList){ toy->
                Log.d("Browse Screen","Lazy Column call")
                ToyCard(toy = toy){
                    val viewVM = ViewModelProvider(context as MainActivity).get(ToyItemViewModel::class.java)
                    viewVM.toy = toy
                    navController.navigate(NavScreens.ViewItemScreen.route)
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
        )
        {
            Text("Loading", style = MaterialTheme.typography.h3)
            Log.d("Browse Screen","Loading Screen")

        }
    }
}