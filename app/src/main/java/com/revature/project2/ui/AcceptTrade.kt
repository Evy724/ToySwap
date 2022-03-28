package com.revature.project2.ui

import android.hardware.camera2.CameraConstrainedHighSpeedCaptureSession
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.revature.project2.R

@Composable
fun AcceptTradeScreen()
{
    Column()
    {
        // Accept trade offer top app bar
        TopAppBar(
            title = { Text(text = "Accept Trade Offer") },
            backgroundColor = MaterialTheme.colors.secondary,
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxSize()
                .background(
                    MaterialTheme.colors.surface,
                    RectangleShape
                )
        )
        {
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Somebody wants to\ntrade with you!",
                fontSize = 25.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = "Proposed Trade:",
                fontSize = 25.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier =
                Modifier.padding(5.dp),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.Center,
            )
            {

                // Left column
                Column(
                    modifier = Modifier.padding(5.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                )
                {

                    Spacer(modifier = Modifier.height(40.dp))
                    // Down arrow
                    Image(
                        painter = painterResource(id = R.drawable.down_arrow),
                        contentDescription = "Down arrow",
                        modifier = Modifier.size(70.dp),
                        alignment = Alignment.BottomCenter
                    )

                    Spacer(modifier = Modifier.height(110.dp))

                    // What you are trading box
                    Surface(
                        modifier = Modifier
                            .height(50.dp)
                            .width(100.dp),
                        border = BorderStroke(
                            width = 2.dp,
                            color = Color.White
                        )
                    )
                    {
                        Text(
                            text = "What you \nare trading\n->",
                            textAlign = TextAlign.Center,
                        )
                    }


                    Spacer(modifier = Modifier.height(64.dp))
                }

                // Middle column
                Column(
                    modifier = Modifier.padding(5.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                )
                {

                    // Toy pic for what the other user is trading
                    Surface()
                    {
                        Image(
                            painter = painterResource(id = R.drawable.pokemon_legends_arceus_nintendo_switch_in_box),
                            contentDescription = "Toy pic of Pokemon Legends Arceus from other user",
                            modifier = Modifier.size(150.dp),
                            alignment = Alignment.TopCenter
                        )
                    }

                    // Toy pic for what you are trading
                    Surface()
                    {
                        Image(
                            painter = painterResource(R.drawable.sleeping_waddle_dee_plushie),
                            contentDescription = "Toy pic of the sleeping Waddle Dee plushie from you",
                            modifier = Modifier.size(150.dp),
                            alignment = Alignment.BottomCenter
                        )
                    }
                }

                // Right column
                Column(
                    modifier = Modifier.padding(5.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                )
                {
                    Spacer(modifier = Modifier.height(40.dp))

                    // What the other user is trading box
                    Surface(
                        modifier = Modifier
                            .height(165.dp)
                            .width(100.dp),
                        border = BorderStroke(
                            width = 2.dp,
                            color = Color.White
                        )
                    )
                    {
                        Text(
                            text = "What the \nother user \nis trading \n<-",
                            textAlign = TextAlign.Center,
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Up arrow
                    Image(
                        painter = painterResource(id = R.drawable.up_arrow),
                        contentDescription = "Up arrow",
                        modifier = Modifier.size(70.dp),
                        alignment = Alignment.TopCenter
                    )
                }
            }

            Text(
                text = "Will you accept this trade?",
                fontSize = 25.sp,
                textAlign = TextAlign.Center
            )

            Row(
                modifier = Modifier.padding(5.dp),
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
                        Text(
                            text = "Accept",
                            fontSize = 15.sp,
                            textAlign = TextAlign.Center
                        )
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
                        Text(
                            text = "Decline",
                            fontSize = 15.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    // Insert bottom bar
    }
}

@Preview
@Composable
fun PreviewAcceptTradeScreen()
{
    AcceptTradeScreen()
}
