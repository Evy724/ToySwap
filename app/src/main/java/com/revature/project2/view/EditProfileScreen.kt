package com.revature.project2.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.revature.project2.R

@Preview(showBackground = true)
@Composable
fun EditProfileScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        ScreenTitle()
        ChangeProfilePictureSection()
        ChangeCredentialsSection()
    }
}
@Composable
fun ScreenTitle() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        contentAlignment = Alignment.TopCenter

    ) {

        Text(
            text = "Edit Profile",
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp
        )
    }
}
@Composable
fun ChangeProfilePictureSection() {
    RoundImage(
        image = painterResource(R.drawable.selfie),
        modifier = Modifier
            .size(100.dp)
    )
    Spacer(modifier = Modifier.width(16.dp))
}
@Composable
fun ChangeCredentialsSection() {


}
