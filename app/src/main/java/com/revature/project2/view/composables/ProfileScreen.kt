package com.revature.project2.view.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.revature.project2.R

//Creates the column for the entire page and populates with profile features
@Preview(showBackground = true)
@Composable
fun ProfileScreen() {

    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state = scrollState)
    ) {
        ProfileSection()
        CurrentlyPostedItems()
        UserReviews()
    }
}
//Populates the ProfileScreen column with the user's profile picture as well as their ProfileInfo
@Composable
fun ProfileSection(modifier: Modifier = Modifier) {

    Column(modifier = modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp, vertical = 20.dp)
        ) {
            RoundImage(
                image = painterResource(R.drawable.selfie),
                modifier = Modifier
                    .size(100.dp)
                    .weight(3f)
            )
            Spacer(modifier = Modifier.width(16.dp))
            StatSection(modifier = Modifier.weight(7f))
        }
        ProfileDescription(
            name = "Evan Jones",
            email = "evan687@revature.net",
            phoneNumber = "(123) 456 789"
        )
    }
}
//Creates a round profile picture for the user
@Composable
fun RoundImage(image: Painter, modifier: Modifier = Modifier) {

    Image(
        painter = image,
        contentDescription = null,
        modifier = modifier
            .aspectRatio(1f, matchHeightConstraintsFirst = true)
            .border(
                width = 1.dp,
                color = Color.LightGray,
                shape = CircleShape
            )
            .padding(3.dp)
            .clip(CircleShape)
    )
}
//Populates the Row with ProfileStats
@Composable
fun StatSection(modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = modifier
    ) {
        ProfileStat(numberText = "10", text = "Posts")
        ProfileStat(numberText = "15", text = "Trades")
        ProfileStat(numberText = "8.5/10", text = "Rating")
    }
}
//Creates the Profile Stat (hard coded for now)
@Composable
fun ProfileStat(
    numberText: String,
    text: String,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = numberText,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = text)
    }
}
//Populates the user's Profile Description
@Composable
fun ProfileDescription(name: String, email: String, phoneNumber: String) {
    val letterSpacing = 0.5.sp
    val lineHeight = 20.sp
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 40.dp)
    ) {
        Text(
            text = name,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            letterSpacing = letterSpacing,
            lineHeight = lineHeight
        )
        Text(
            text = email,
            color = Color(0xFF3D3D91),
            letterSpacing = letterSpacing,
            lineHeight = lineHeight
        )
        Text(
            text = phoneNumber,
            letterSpacing = letterSpacing,
            lineHeight = lineHeight
        )
    }
}
//Displays the account's currently posted items
@Composable
fun CurrentlyPostedItems() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Currently Posted Items",
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp
            )
        }
        //Implementation of Ryan's LazyColumn list of ToyCards
//        LazyColumn(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            itemsIndexed(toyList) { _, item ->
//                ToyCard(toy = item)
//            }
//        }
    }
}
@Composable
fun UserReviews() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)

    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "User Reviews",
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp
            )
        }
        //Implementation of Ryan's LazyColumn list of ReviewCards
//        LazyColumn(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            itemsIndexed(reviewList) { _, item ->
//                ReviewCard(review = item)
//            }
//        }
    }
}