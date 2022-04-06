package com.revature.project2.view.composables

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.revature.project2.MainActivity
import com.revature.project2.R
import com.revature.project2.model.api.user.Review
import com.revature.project2.model.api.user.User
import com.revature.project2.ui.theme.BluishGreen
import com.revature.project2.ui.theme.PurpleVariant
import com.revature.project2.view.nav.NavScreens
import com.revature.project2.viewmodel.*

//Creates the column for the entire page and populates with profile features
@Composable
fun MyProfileScreen(
    navController: NavController
) {
    val scaffoldState = rememberScaffoldState(
        rememberDrawerState(DrawerValue.Closed)
    )

    Scaffold(scaffoldState = scaffoldState,

        content =
        {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
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
                Header(text = "My Profile")

                Surface(
                    modifier = Modifier
                        .border(
                            width = 2.dp,
                            brush = Brush.horizontalGradient(
                                colors = listOf(
                                    PurpleVariant,
                                    BluishGreen
                                )
                            ),
                            shape = AbsoluteRoundedCornerShape(topLeft = 10.dp, topRight = 10.dp)
                        )
                        .clip(
                            shape = RoundedCornerShape(10.dp)
                        )
                )
                {
                    Surface()
                    {
                        val scrollState = rememberLazyListState()

                        LazyColumn(
                            state = scrollState,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(bottom = 50.dp),
//                    .verticalScroll(state = scrollState)
                        )
                        {
                            item()
                            {
                                MyProfileSection()
                            }
                            item()
                            {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
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
                                    Surface(
                                        modifier = Modifier
                                            .border(
                                                width = 2.dp,
                                                brush = Brush.horizontalGradient(
                                                    colors = listOf(
                                                        PurpleVariant,
                                                        BluishGreen
                                                    )
                                                ),
                                                shape = AbsoluteRoundedCornerShape(
                                                    topLeft = 10.dp,
                                                    topRight = 10.dp
                                                )
                                            )
                                            .clip(
                                                shape = RoundedCornerShape(10.dp)
                                            )
                                    )
                                    {
                                        MyPostHistory()
                                    }

                                }

                            }
                            item()
                            {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
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
                                    Surface(
                                        modifier = Modifier
                                            .border(
                                                width = 2.dp,
                                                brush = Brush.horizontalGradient(
                                                    colors = listOf(
                                                        PurpleVariant,
                                                        BluishGreen
                                                    )
                                                ),
                                                shape = AbsoluteRoundedCornerShape(
                                                    topLeft = 10.dp,
                                                    topRight = 10.dp
                                                )
                                            )
                                            .clip(
                                                shape = RoundedCornerShape(10.dp)
                                            )
                                    )
                                    {
                                        MyUserReviews()
                                    }
                            }
                        }
                    }
                }
            }
        },
        bottomBar = { BottomBar(navController) }
    )
}
//Populates the ProfileScreen column with the user's profile picture as well as their ProfileInfo
@Composable
fun MyProfileSection(modifier: Modifier = Modifier)
{

    val context = LocalContext.current
    Column(modifier = modifier.fillMaxWidth())
    {
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp, vertical = 20.dp)
        )
        {
            MyRoundImage(
                image = painterResource(R.drawable.selfie),
                modifier = Modifier
                    .size(100.dp)
                    .weight(3f)
            )
            Spacer(modifier = Modifier.width(16.dp))
            MyStatSection(modifier = Modifier.weight(7f))
        }

        //val profileViewModel = ProfileViewModel()
        val profileViewModel = ViewModelProvider(context as MainActivity).get(ProfileViewModel::class.java)
        val browseViewModel = ViewModelProvider(context as MainActivity).get(AllToysViewModel::class.java)
        MyProfileDescription(
            name = "${browseViewModel.currentUser!!.sName} ",
            email = "${browseViewModel.currentUser!!.sEmail}",
            phoneNumber = "(123) 456 789"
        )
        Spacer(modifier = Modifier.height(10.dp))
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
fun MyPostHistory()
{
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    )
    {
        Spacer(modifier = Modifier.height(10.dp))
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        )
        {
            Text(
                text = "Post History",
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp
            )
        }
        val context = LocalContext.current
        //Implementation of Ryan's LazyColumn list of ToyCards
        val userToysViewModel = ViewModelProvider(context as MainActivity).get(UserToysViewModel::class.java)
        val toyList = userToysViewModel.userToys

        toyList.forEach { toy->
            ToyCard(toy = toy) {
                //Clicked code
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
//        LazyColumn(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            itemsIndexed(toyList) { _, item ->
//                ToyCard(toy = item){
//                    //What happens when clicked?
//                }
//            }
//        }
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
        val context = LocalContext.current
        val reviewViewModel = ViewModelProvider(context as MainActivity).get(ReviewViewModel::class.java)
        val browseViewModel = ViewModelProvider(context as MainActivity).get(AllToysViewModel::class.java)
        //Implementation of Ryan's LazyColumn list of ReviewCards
        reviewViewModel.getReviews(User(browseViewModel.currentUser!!.nUserId))
        var reviewList:List<Review> = reviewViewModel.userReviews

        //Dummy reviewlist
        reviewList = listOf(

            Review(
                review_id = 0,
                user = User(0),
                description = "good",
                rating = 4
            ),
            Review(
                review_id = 1,
                user = User(1),
                description = "poor",
                rating = 2
            )
        )

        reviewList.forEach { review ->
                //dummy code
                Card(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .clickable {

                        },
                    shape = MaterialTheme.shapes.medium,
                    elevation = 5.dp,
                    backgroundColor = MaterialTheme.colors.surface
                ) {
                    Row(){
                        Text(text = " User Rating: ${review.rating}", style = MaterialTheme.typography.h3)
                    }
                }

        }
//        LazyColumn(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            items(reviewList) { review ->
//                //ReviewCard(review)
//
//                //dummy code
//                Card(
//                    modifier = Modifier
//                        .padding(10.dp)
//                        .fillMaxWidth()
//                        .height(100.dp)
//                        .wrapContentHeight()
//                        .clickable {
//
//                        },
//                    shape = MaterialTheme.shapes.medium,
//                    elevation = 5.dp,
//                    backgroundColor = MaterialTheme.colors.surface
//                ) {
//                    Row(){
//                        Text(text = " User Rating: ${review.rating}")
//                    }
//                }
//            }
//        }
    }
}