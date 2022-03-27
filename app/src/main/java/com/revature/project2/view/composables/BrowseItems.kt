package com.revature.project2.view.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.revature.project2.viewmodel.AllToysViewModel

/**
 * Browse Items screen using scaffold
 *
 * Uses ToyCard and BottomBar from shared file
 */
@Composable
fun BrowseItemsScreen(navController: NavController, viewModel:AllToysViewModel){

    val scaffoldState = rememberScaffoldState(
        rememberDrawerState(DrawerValue.Closed))

    Scaffold(scaffoldState = scaffoldState,
        topBar = { TopAppBar( title = {Text("Browse Items: ")},
        backgroundColor = MaterialTheme.colors.secondary) },
        content = {

            val lazyState = rememberLazyListState()
            val toyList = viewModel.allToys

            if (toyList.isNotEmpty())
            {
                LazyColumn(state = lazyState,
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally){

                    itemsIndexed(toyList) { _, item ->
                        ToyCard(toy = item)
                    }
                }
            }
            else {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text("Loading", style = MaterialTheme.typography.h3)

                }
            }
        },
        bottomBar = { BottomBar(navController) })
}