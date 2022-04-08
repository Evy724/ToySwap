package com.revature.project2.view.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.revature.project2.R
import com.revature.project2.ui.theme.BluishGreen
import com.revature.project2.ui.theme.PurpleVariant


@Composable
fun NewToyPostScreen(navController: NavController) {
    Scaffold(bottomBar = { BottomBar(navController) })
    {
        Surface()
        {
            Image(
                painter = painterResource(id = R.drawable.minimal_blue_toy_background),
                contentDescription = "he",
                modifier = Modifier
                    .height(1000.dp)
                    .width(700.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.FillBounds
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
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

                screenTitle()
                inputToyName()
                toyDescription()
                ageOfToy()
                postToy(navController)
            }
        }

    }
}
