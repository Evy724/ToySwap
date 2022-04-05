package com.revature.project2.view.composables

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.revature.project2.MainActivity
import com.revature.project2.R
import com.revature.project2.model.api.alltoys.ToyItem
import com.revature.project2.model.api.allusers.User
import com.revature.project2.ui.theme.Project2Theme
import com.revature.project2.ui.theme.Project2Typography
import com.revature.project2.ui.theme.Teal200
import com.revature.project2.ui.theme.TealGreen
import com.revature.project2.view.nav.NavScreens
import com.revature.project2.viewmodel.AllToysViewModel
import com.revature.project2.viewmodel.LoginViewModel

@Composable
fun Login(navController: NavController
){

    Log.d("Login Screen","Login Screen Start")

    val scaffoldState = rememberScaffoldState()

    Scaffold (
        scaffoldState = scaffoldState,
        content =
        {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            )
            {
                Header(text = "Log in")
                MainToyPosterImage()
                LoginBody(
                    navController = navController
                )
            }

        }
    )
}
@Composable
fun LoginBody(navController: NavController)
{

    val context = LocalContext.current
    var loginButtonText by rememberSaveable { mutableStateOf("Loading") }
    var bEnabled  by rememberSaveable { mutableStateOf(false) }
    var sName by rememberSaveable { mutableStateOf("") }
    var sPass by rememberSaveable { mutableStateOf("") }
    val loginViewModel = ViewModelProvider(context as MainActivity).get(LoginViewModel::class.java)

    val userList = loginViewModel.allUsers

    if (userList.isNotEmpty()){
        loginButtonText = "Log In:"
        bEnabled = true
        Log.d("Login Screen","All Users Loaded")
    }

    Log.d("Login Screen","Scaffold Content")

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,

    )
    {

        Spacer(Modifier.size(10.dp))

        ToySwapLogo()

        Spacer(Modifier.size(10.dp))

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

        Spacer(modifier = Modifier.size(40.dp))


        Button(
            enabled = bEnabled,
            onClick = {
                Log.d("Login Screen","Login Button Clicked")

                //Disable button and change text to Loading
                loginButtonText = "Loading"
                bEnabled = false

                //Check if the user exists in our server
                var user:User? = loginViewModel.existingUserCheck(sName,sPass)
                if (user != null){

                    val browseVM =
                        ViewModelProvider(context as MainActivity).get(AllToysViewModel::class.java)
                    browseVM.currentUser = user
                    Log.d("Login Screen","Current User Set")

                    //If it does, log in with that user
                    loginViewModel.login(sName,sPass)
                    navController.navigate(NavScreens.BrowseItemsScreen.route)
//                            if (loginViewModel.login(sName,sPass)){
//
//                                Log.d("Login Screen","Nav to Browse Screen")
//                                navController.navigate(NavScreens.BrowseItemsScreen.route)
//                            } else {
//
//                                Toast.makeText(
//                                    context,
//                                    "Invalid Login",
//                                    Toast.LENGTH_LONG).show()
//                                loginButtonText = "Login"
//                                bEnabled = true
//                            }
                }
                else
                {

                    //If it doesnt, reset the Screen
                    Toast.makeText(
                        context,
                        "Invalid Login",
                        Toast.LENGTH_LONG).show()
                    bEnabled = true
                    loginButtonText = "Log In:"
                    sName = ""
                    sPass = ""
                    Log.d("Login Screen","Login Failed")
                }

            },
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth(.5f)) {

            Text(loginButtonText)

        }

        Spacer(modifier = Modifier.size(15.dp))

        Text(
            text = "New User? Register",
            modifier = Modifier
                .clickable {
                    Log.d("Login Screen","Register User Clicked")

                    navController.navigate(NavScreens.RegisterScreen.route)
                },
            style = Project2Typography.body2
        )
    }

}
