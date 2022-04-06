package com.revature.project2.view.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.revature.project2.view.nav.NavScreens

@Composable
fun Register(navController: NavController){

    val scaffoldState = rememberScaffoldState()

    Scaffold (
        scaffoldState = scaffoldState,
        content =
        {
            Header(text = "Register")
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                var sName by rememberSaveable { mutableStateOf("") }
                var sPass by rememberSaveable { mutableStateOf("") }
                var sPassConfirm by rememberSaveable { mutableStateOf("") }
                var sEmail by rememberSaveable { mutableStateOf("") }

                Spacer(Modifier.size(60.dp))

                Text(
                    text ="Sign Up",
                    style = MaterialTheme.typography.h4)

                Spacer(Modifier.size(60.dp))

                TextField(
                    value = sName,
                    onValueChange = { sName = it},
                    label = {Text("Username: ")})

                Spacer(modifier = Modifier.size(10.dp))

                TextField(
                    value = sPass,
                    onValueChange = { sPass = it},
                    label = {Text("Password: ")})

                Spacer(modifier = Modifier.size(10.dp))

                TextField(
                    value = sPassConfirm,
                    onValueChange = { sPassConfirm = it},
                    label = {Text("Confirm Password: ")})

                Spacer(modifier = Modifier.size(10.dp))

                TextField(
                    value = sEmail,
                    onValueChange = { sEmail = it},
                    label = {Text("Email: ")})

                Spacer(modifier = Modifier.size(10.dp))

                universalButton20sp(
                    enabled = true,
                    text = "Register",
                    onClick = {
                        navController.navigate(NavScreens.LoginScreen.route)
                    },
                    modifier = Modifier
                        .padding(5.dp)
                        .fillMaxWidth(.5f)
                )
            }
        }
    )


}