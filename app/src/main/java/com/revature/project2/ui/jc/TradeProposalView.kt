package com.revature.project2.ui.jc


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.revature.project2.Greeting
import com.revature.project2.R
import com.revature.project2.ui.theme.Project2Theme

@Composable
fun tradeProposalScreen()
{
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        TopAppBar() {
            Text(text = "Trade Proposal")
        }
        Spacer(modifier = Modifier.height(20.dp))
        Card(modifier = Modifier.width(250.dp), border = BorderStroke(3.dp, Color.Black)) {
            Text(text = "Average response time: ", textAlign= TextAlign.Center)
        }
        Spacer(modifier = Modifier.height(20.dp))
        Image(painter = painterResource(id = R.drawable.lego), contentDescription = "",
            modifier = Modifier
                .width(150.dp)
                .height(150.dp)
                .border(width = 3.dp, color = Color.Black))
        Spacer(modifier = Modifier.height(50.dp))
        Row(horizontalArrangement = Arrangement.Center) {
            Image(painter = painterResource(id = R.drawable._4603), contentDescription = "",modifier = Modifier.size(70.dp))
            Spacer(modifier = Modifier.width(100.dp))
            Image(painter = painterResource(id = R.drawable._4603reverse), contentDescription = "", modifier = Modifier.size(70.dp))

        }
        Spacer(modifier = Modifier.height(20.dp))
        Card(modifier = Modifier.fillMaxWidth(), border = BorderStroke(3.dp, Color.Black)) {
            Text(text = "MY TOYS ", textAlign= TextAlign.Center)
        }
    }
}

@Composable
@Preview
fun preview() {
    Project2Theme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            tradeProposalScreen()

        }
    }
}