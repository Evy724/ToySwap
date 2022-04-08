package com.revature.project2.view.composables

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.revature.project2.R

@Composable
fun EditProfileScreen(navController: NavController) {
    val scaffoldState = rememberScaffoldState(
        rememberDrawerState(DrawerValue.Closed)
    )
    Scaffold(scaffoldState = scaffoldState, content = {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            ScreenTitle()
            ChangeProfilePictureSection()
            ChangeCredentialsSection()
        }
        
    },
        bottomBar = { BottomBar(navController = navController)}
    )
}
@Composable
fun ScreenTitle() {
        Header(text = "Edit Profile")
}
@Composable
fun ChangeProfilePictureSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        contentAlignment = Alignment.TopCenter) {
        RoundImage(
            image = painterResource(R.drawable.selfie),
            modifier = Modifier
                .size(100.dp)
        )
    }
    Spacer(modifier = Modifier.width(16.dp))
}
@Composable
fun ChangeCredentialsSection() {
    var sName by rememberSaveable { mutableStateOf("") }
    var sPass by rememberSaveable { mutableStateOf("") }
    var sEmail by rememberSaveable { mutableStateOf("") }
    var sNumber by rememberSaveable { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Box(modifier = Modifier.padding(50.dp), contentAlignment = Alignment.Center) {
                    Column {
                        TextField(
                            value = sName,
                            onValueChange = { sName = it },
                            label = { Text("Change username: ") }
                        )
                        Spacer(modifier = Modifier.size(10.dp))
                        TextField(
                            value = sPass,
                            onValueChange = { sPass = it },
                            label = { Text("Change password: ") }
                        )
                        Spacer(modifier = Modifier.size(10.dp))
                        TextField(
                            value = sEmail,
                            onValueChange = { sEmail = it },
                            label = { Text("Change email: ") }
                        )
                        Spacer(modifier = Modifier.size(10.dp))
                        TextField(
                            value = sNumber,
                            onValueChange = { sNumber = it },
                            label = { Text("Change phone number: ") }
                        )
                        Spacer(modifier = Modifier.size(10.dp))
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
                            universalButton20sp(
                                enabled = true,
                                text = "Save",
                                onClick = {
                                    Toast.makeText(context, "Credentials saved.", Toast.LENGTH_LONG).show()
                                },
                                modifier = Modifier
                                    .padding(5.dp)
                                    .fillMaxWidth(.5f)
                            )
                        }
                    }
                }
            }
        }
    }
}
