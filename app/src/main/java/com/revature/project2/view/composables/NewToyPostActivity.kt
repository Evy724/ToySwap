package com.revature.project2.view.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController



@Composable
fun NewToyPostScreen(navController: NavController)
{
Scaffold(bottomBar = {BottomBar(navController)})
{
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .background(
                MaterialTheme.colors.surface,
                RectangleShape
            )
            .fillMaxWidth()
    )
    {

        screenTitle()
        inputToyName()
        toyDescription()
        ageOfToy()
        postToy(navController)
    }
}

}
