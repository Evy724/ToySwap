package com.revature.project2.view.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.revature.project2.ui.theme.Project2Typography
import com.revature.project2.ui.theme.Purple200
import com.revature.project2.ui.theme.Purple500
import com.revature.project2.ui.theme.TealGreen
import com.revature.project2.view.nav.NavScreens

@Composable
fun Register(navController: NavController){

    val scaffoldState = rememberScaffoldState()

    Scaffold (
        topBar= {Header(text = "Register")},
        scaffoldState = scaffoldState,
        backgroundColor = Purple200,
        content =
        {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally)
            {
                Spacer(Modifier.size(60.dp))
                Card(shape = RoundedCornerShape(25.dp), elevation = 20.dp) {
                    Column(
                        modifier = Modifier.fillMaxSize(fraction = 0.9F),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        var sName by rememberSaveable { mutableStateOf("") }
                        var sPass by rememberSaveable { mutableStateOf("") }
                        var sPassConfirm by rememberSaveable { mutableStateOf("") }
                        var sEmail by rememberSaveable { mutableStateOf("") }

                        Spacer(Modifier.size(60.dp))
                        Text(text = "Create New Account", fontSize = 30.sp,
                            fontWeight = FontWeight.Medium,color=Purple200)
                        Spacer(Modifier.size(60.dp))

                        TextField(
                            modifier=Modifier.padding(10.dp) ,
                            value = sName,
                            onValueChange = { sName = it },
                            label = { Text("Username: ") })

                        Spacer(modifier = Modifier.size(10.dp))

                        TextField(
                            modifier=Modifier.padding(10.dp) ,
                            value = sPass,
                            onValueChange = { sPass = it },
                            label = { Text("Password: ") })

                        Spacer(modifier = Modifier.size(10.dp))

                        TextField(
                            modifier=Modifier.padding(10.dp) ,
                            value = sPassConfirm,
                            onValueChange = { sPassConfirm = it },
                            label = { Text("Confirm Password: ") })

                        Spacer(modifier = Modifier.size(10.dp))

                        TextField(
                            modifier=Modifier.padding(10.dp) ,
                            value = sEmail,
                            onValueChange = { sEmail = it },
                            label = { Text("Name") })

                        Spacer(modifier = Modifier.size(20.dp))

                        universalButton20sp(
                            enabled = true,
                            text = "Register",
                            onClick = {
                                if (checkParams(sName,sPass,sEmail,sPassConfirm)&& checkMatch(sPass,sPassConfirm)){
                                    navController.navigate(NavScreens.LoginScreen.route)
                                }

                            },
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth(.5f)
                        )
                    }
                }
            }

        }
    )


}
fun checkParams(a:String,b:String,c:String,d:String):Boolean
{
    return a.isNotEmpty() && b.isNotEmpty() && c.isNotEmpty() && d.isNotEmpty()

}
fun checkMatch(a: String,b: String)=a.equals(b)
