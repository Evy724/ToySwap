package com.revature.project2.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.R
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun AcceptTradeScreen()
{
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .background(
                MaterialTheme.colors.surface,
                RectangleShape
            )
    )
    {
        // Accept trade offer top app bar
        TopAppBar(
            title = { Text(text = "Accept Trade Offer") },
            backgroundColor = MaterialTheme.colors.secondary
        )

        Row(
            modifier =
            Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        )
        {

            // Left column
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {

                Spacer(modifier = Modifier.height(20.dp))

                // Down arrow
                Image(
                    painter = painterResource(id = R.drawable.down_arrow),
                    contentDescription = "Down arrow",
                    modifier = Modifier.size(150.dp),
                    alignment = Alignment.TopCenter
                )

                // What you are trading box
                Surface(
                    modifier = Modifier
                        .height(165.dp)
                        .width(250.dp),
                    border = BorderStroke(
                        width = 2.dp,
                        color = colorResource(id = R.color.animalCrossingGreen)
                    )
                )
                {
                    Text(
                        text = "What you are trading",
                        textAlign = TextAlign.Center,
                    )
                }


                Spacer(modifier = Modifier.height(64.dp))
            }

            // Middle column
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                Spacer(modifier = Modifier.height(15.dp))

                // Toy pic for what the other user is trading
                Surface()
                {
                    Image(
                        painter = painterResource(id = R.drawable.toy_pic_from_other_user),
                        contentDescription = "Toy pic from other user",
                        modifier = Modifier.size(150.dp),
                        alignment = Alignment.BottomCenter
                    )
                }

                // Toy pic for what you are trading
                Surface()
                {
                    Image(
                        painter = painterResource(R.drawable.toy_pic_from_you),
                        contentDescription = "Toy pic from you",
                        modifier = Modifier.size(150.dp),
                        alignment = Alignment.BottomCenter
                    )
                }
            }

            // Right column
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                // What the other user is trading box
                Surface(
                    modifier = Modifier
                        .height(165.dp)
                        .width(250.dp),
                    border = BorderStroke(
                        width = 2.dp,
                        color = colorResource(id = R.color.animalCrossingGreen)
                    )
                )
                {
                    Text(
                        text = "What the other user is trading",
                        textAlign = TextAlign.Center,
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Up arrow
                Image(
                    painter = painterResource(id = R.drawable.up_arrow),
                    contentDescription = "Up arrow",
                    modifier = Modifier.size(150.dp),
                    alignment = Alignment.TopCenter
                )
            }
        }

        Text(text = "Will you accept this trade?")

        Row(
            modifier =
            Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        )
        {
            // Left accept button
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                Button(onClick = { /*TODO*/ })
                {
                    Text(text = "Accept")
                }
            }

            // Right decline button
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                Button(onClick = { /*TODO*/ })
                {
                    Text(text = "Decline")
                }
            }
        }
    }
}

