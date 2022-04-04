package com.revature.project2.view.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.revature.project2.R
import com.revature.project2.model.api.user.User
import com.revature.project2.viewmodel.HistoryViewModel
import com.revature.project2.viewmodel.ProfileViewModel
import com.revature.project2.viewmodel.ReviewViewModel
import com.revature.project2.viewmodel.UserToysViewModel

//Creates the column for the entire page and populates with profile features
@Composable
fun MyProfileScreen(
    navController: NavController,
    userToysViewModel: UserToysViewModel,
    profileViewModel: ProfileViewModel,
    historyViewModel: HistoryViewModel,
    reviewViewModel: ReviewViewModel
) {
    val scaffoldState = rememberScaffoldState(
        rememberDrawerState(DrawerValue.Closed)
    )

    Scaffold(scaffoldState = scaffoldState,
        topBar = { TopAppBar( title = {Text("My Profile: ")},
            backgroundColor = MaterialTheme.colors.secondary) },
        content = {

            val scrollState = rememberScrollState()

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(state = scrollState)
            ) {
                MyProfileSection()
                MyPostHistory()
                MyUserReviews()
            }
            bottomBar = { BottomBar(navController) }
        })
}
//Populates the ProfileScreen column with the user's profile picture as well as their ProfileInfo
@Composable
fun MyProfileSection(modifier: Modifier = Modifier) {

    val context = LocalContext.current
    Column(modifier = modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp, vertical = 20.dp)
        ) {
            MyRoundImage(
                image = painterResource(R.drawable.selfie),
                modifier = Modifier
                    .size(100.dp)
                    .weight(3f)
            )
            Spacer(modifier = Modifier.width(16.dp))
            MyStatSection(modifier = Modifier.weight(7f))
        }
        val profileViewModel = ProfileViewModel()
        MyProfileDescription(
            name = "${profileViewModel.first_name} "+"${profileViewModel.last_name}",
            email = "${profileViewModel.email}",
            phoneNumber = "(123) 456 789"
        )
    }
}
//Creates a round profile picture for the user
@Composable
fun MyRoundImage(image: Painter, modifier: Modifier = Modifier) {

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
fun MyStatSection(modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = modifier
    ) {
        MyProfileStat(numberText = "10", text = "Posts")
        MyProfileStat(numberText = "15", text = "Trades")
        MyProfileStat(numberText = "8.5/10", text = "Rating")
    }
}
//Creates the Profile Stat (hard coded for now)
@Composable
fun MyProfileStat(
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
fun MyProfileDescription(name: String, email: String, phoneNumber: String) {
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
fun MyPostHistory() {
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
                text = "Post History",
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp
            )
        }
        //Implementation of Ryan's LazyColumn list of ToyCards
        val toyList = userToysViewModel.userToys
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            itemsIndexed(toyList) { _, item ->
                ToyCard(toy = item)
            }
        }
    }
}
@Composable
fun MyUserReviews() {
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
        val reviewList = reviewViewModel.getReviews
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            itemsIndexed(reviewList) { _, item ->
                ReviewCard(review = item)
            }
        }
    }
}