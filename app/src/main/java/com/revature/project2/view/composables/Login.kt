package com.revature.project2.view.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.revature.project2.view.nav.NavScreens

@Composable
fun Login(navController: NavController){

    val scaffoldState = rememberScaffoldState()

    Scaffold (
        scaffoldState = scaffoldState,
        topBar = { TopAppBar( title = { Text("Log in: ") },
            backgroundColor = MaterialTheme.colors.secondary) },
        content = {

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                var sName by rememberSaveable { mutableStateOf("") }
                var sPass by rememberSaveable { mutableStateOf("") }

                Spacer(Modifier.size(60.dp))

                Text(
                    text ="Toys R' Swapped",
                    style = MaterialTheme.typography.h3)

                Spacer(Modifier.size(60.dp))

                TextField(
                    value = sName,
                    onValueChange = { sName = it},
                    label = {Text("Username: ")})

                Spacer(modifier = Modifier.size(10.dp))

                TextField(
                    value = sPass,
                    onValueChange = { sPass = it},
                    label = {Text("Password: ")},
                    visualTransformation = PasswordVisualTransformation())

                Spacer(modifier = Modifier.size(30.dp))

                Button(
                    onClick = {
                              navController.navigate(NavScreens.BrowseItemsScreen.route)
                    },
                    modifier = Modifier
                        .padding(5.dp)
                        .fillMaxWidth(.5f)) {

                    Text("Login")

                }

                Spacer(modifier = Modifier.size(10.dp))

                Text(
                    text = "New User? Register",
                    modifier = Modifier
                        .clickable {

                                   navController.navigate(NavScreens.RegisterScreen.route)
                        },
                    style = MaterialTheme.typography.body1
                )

            }
        }


    )
}