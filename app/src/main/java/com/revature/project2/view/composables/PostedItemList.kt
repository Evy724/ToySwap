package com.revature.project2.view.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.revature.project2.MainActivity
import com.revature.project2.view.nav.NavScreens
import com.revature.project2.viewmodel.AllToysViewModel
import com.revature.project2.viewmodel.ToyItemViewModel
import com.revature.project2.viewmodel.UserToysViewModel

@Composable
fun PostedItemsScreen(navController: NavController){

    val scaffoldState = rememberScaffoldState()
    val context = LocalContext.current

    val userToysViewModel =
        ViewModelProvider(context as MainActivity).get(UserToysViewModel::class.java)

    userToysViewModel.getUserOffers()

    val itemViewModel =
        ViewModelProvider(context).get(ToyItemViewModel::class.java)

    val offerlist = userToysViewModel.userTradeOffers

    Scaffold(scaffoldState = scaffoldState,
        topBar = { TopAppBar( title = { Text("Posted Items: ") },
            backgroundColor = MaterialTheme.colors.secondary) },
        floatingActionButton = {
           FloatingActionButton(onClick = {

           }) {
               Icon(Icons.Filled.Add,"")

           }
        },
        content = {
            
            Column(
                modifier = Modifier
                    .fillMaxHeight(.7f),
                horizontalAlignment = Alignment.CenterHorizontally)
            {

                if (userToysViewModel.userToys.isNotEmpty()) {


                    val lazyState = rememberLazyListState()
                    val toyList = userToysViewModel.userToys

                    LazyColumn(
                        state = lazyState,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 50.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        itemsIndexed(toyList) { _, item ->

                            var bNotice = false
                            offerlist.forEach {
                                if (item.id == it.nUserToyId)
                                    bNotice = true
                            }

                            ToyCard(toy = item, bNotification = bNotice){
                                itemViewModel.toy = item
                                navController.navigate(NavScreens.ViewItemScreen.route)
                            }
                        }
                    }
                } else {

                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text("Loading", style = MaterialTheme.typography.h3)
                    }
                }
            }
        },
        bottomBar = {BottomBar(navController = navController)}
    )
}