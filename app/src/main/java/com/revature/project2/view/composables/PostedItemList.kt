package com.revature.project2.view.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.revature.project2.viewmodel.UserToysViewModel

@Composable
fun PostedItemsScreen(navController: NavController,userToysViewModel: UserToysViewModel){

    val scaffoldState = rememberScaffoldState()

    Scaffold(scaffoldState = scaffoldState,
        topBar = { TopAppBar( title = { Text("Posted Items: ") },
            backgroundColor = MaterialTheme.colors.secondary) },
        content = {
            
            Column(modifier = Modifier.fillMaxHeight(.7f),
                horizontalAlignment = Alignment.CenterHorizontally)
            {

                if (userToysViewModel.userToys.isNotEmpty()) {


                    val lazyState = rememberLazyListState()
                    val toyList = userToysViewModel.userToys

                    LazyColumn(
                        state = lazyState,
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        itemsIndexed(toyList) { _, item ->
                            ToyCard(toy = item)
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

                Spacer(Modifier.size(40.dp))
                Button(modifier =
                    Modifier.padding(5.dp)
                    .fillMaxWidth(.5f),
                    onClick = {
                        
                    }) {
                    Text(text = "Post Toy")
                    
                }
                
                
            }
        },
        bottomBar = {BottomBar(navController = navController)}
    )
}