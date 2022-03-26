package com.revature.project2.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.coil.rememberCoilPainter
import com.revature.project2.model.api.alltoys.ToyItem
import com.revature.project2.view.composables.ToyCard
import com.revature.project2.viewmodel.AllToysViewModel

@Composable
fun BrowseItemsScreen(navController: NavController, viewModel:AllToysViewModel){

    var lazyState = rememberLazyListState()


    var toyList = viewModel.allToys

    LazyColumn(state = lazyState,
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally){

        if (toyList.isNotEmpty())
        {
            itemsIndexed(toyList) { _, item ->
                ToyCard(toy = item)
            }
        }
    }
}