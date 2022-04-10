package com.revature.project2.view.composables

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.revature.project2.MainActivity
import com.revature.project2.R
import com.revature.project2.model.AppManager
import com.revature.project2.model.DataManager
import com.revature.project2.model.DataStore
import com.revature.project2.model.api.allusers.User
import com.revature.project2.ui.theme.BluishGreen
import com.revature.project2.ui.theme.Project2Typography
import com.revature.project2.ui.theme.PurpleVariant
import com.revature.project2.view.nav.NavScreens
import com.revature.project2.viewmodel.AllToysViewModel
import com.revature.project2.viewmodel.LoginViewModel
import com.revature.project2.viewmodel.UserToysViewModel
//import com.revature.project2.viewmodel.UserToysViewModelFactory
import kotlinx.coroutines.flow.collect

@Composable
fun Login(
    navController: NavController
)
{

    Log.d("Login Screen","Login Screen Start")

    val scaffoldState = rememberScaffoldState()

    Scaffold (
        scaffoldState = scaffoldState,
        content =
        {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
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
                MainToyPosterImage()
                Surface(
                    Modifier
                        .clip(shape = AbsoluteRoundedCornerShape(topLeft = 10.dp, topRight = 10.dp))
                        .width(400.dp)
                        .fillMaxHeight()
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

                )
                {
                    LoginBody(navController = navController)
                }
            }
        }
    )
}





@Composable
fun LoginBody(navController: NavController){


    val context = LocalContext.current
    val dataStore = DataStore(context)
    var loginButtonText by rememberSaveable { mutableStateOf("Loading") }
    var bEnabled by rememberSaveable { mutableStateOf(false) }
    var sName by rememberSaveable { mutableStateOf("") }
    var sStoredName = dataStore.getUsername.collectAsState(initial = "")
    var sPass by rememberSaveable { mutableStateOf("") }
    var sStoredPass = dataStore.getPassword.collectAsState(initial = "")
    val loginViewModel =
        ViewModelProvider(context as MainActivity).get(LoginViewModel::class.java)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    )
    {




        if (loginViewModel.bUsersLoaded.value || !DataManager.checkNetAccess(context)){
            loginButtonText = "Log In"
            bEnabled = true
            Log.d("Login Screen", "All Users Loaded")
        }

        Log.d("Login Screen", "Scaffold Content")

        Spacer(Modifier.size(10.dp))

        ToySwapLogo()

        Spacer(Modifier.size(10.dp))

        TextField(
            value = sName,
            onValueChange = { sName = it },
            label = { Text("Username: ") },
            placeholder = {Text(sStoredName.value!!)})

        Spacer(modifier = Modifier.size(10.dp))

        TextField(
            value = sPass,
            onValueChange = { sPass = it },
            label = { Text("Password: ") },
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.size(40.dp))

        if(!bEnabled) {
            CircularProgressIndicator()
        } else {


            universalButton20sp(
                enabled = bEnabled,
                text = loginButtonText,
                onClick =
                {
                    Log.d("Login Screen", "Login Button Clicked")

                    //Disable button and change text to Loading
                    loginButtonText = "Loading"
                    bEnabled = false

                    //check if network connected
                    if (DataManager.checkNetAccess(context)){
                        //If connected to internet try to log in
                        if (!loginViewModel.loginWithNet(sName,sPass,context,navController)){

                            //If we dont log in, reset screen
                            Toast.makeText(
                                context,
                                "Invalid Login",
                                Toast.LENGTH_LONG
                            ).show()
                            bEnabled = true
                            loginButtonText = "Log In:"
                            sName = ""
                            sPass = ""
                            Log.d("Login Screen", "Login Failed")
                        }

                    } else {
                        //if not connected, use data store

                        //Check if we had a stored value
                        if(sStoredName.value != ""){

                            //if the username and pass match the stored values
                            if (sName == sStoredName.value && sPass == sStoredPass.value){

                                //var postUserVMFactory = UserToysViewModelFactory(user, context.applicationContext as Application)

                                navController.navigate(NavScreens.PostedItemListScreen.route)

                            }
                        }


                    }
                },
            )

            Spacer(modifier = Modifier.size(15.dp))

            //Register row
            Row {
                Text (
                    text = "New User? ",
                    style = Project2Typography.body2
                )

                Text(
                    text = "Register",
                    modifier = Modifier
                        .clickable {
                            Log.d("Login Screen", "Register User Clicked")

                            navController.navigate(NavScreens.RegisterScreen.route)
                        },
                    style = Project2Typography.body2,
                    color = Color.Blue
                )

            }
        }

    }

}
