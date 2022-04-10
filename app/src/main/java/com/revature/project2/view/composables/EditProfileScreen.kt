package com.revature.project2.view.composables

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.revature.project2.MainActivity
import com.revature.project2.R
import com.revature.project2.ui.theme.Purple200
import com.revature.project2.viewmodel.AllToysViewModel
import com.revature.project2.viewmodel.ProfileViewModel

@OptIn(ExperimentalCoilApi::class)
@Composable
fun EditProfileScreen(navController: NavController){
    val context = LocalContext.current

    val scaffoldState = rememberScaffoldState()

    Scaffold (
        topBar= {Header(text = "Edit Profile")},
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
                        var sEmail by rememberSaveable { mutableStateOf("")}

                        ViewModelProvider(context as MainActivity).get(ProfileViewModel::class.java)
                        val browseViewModel = ViewModelProvider(context).get(AllToysViewModel::class.java)

                        ChangeImage()

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 4.dp, end = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Username:",
                                modifier = Modifier
                                    .width(100.dp),
                                color = Purple200
                            )
                            TextField(
                                value = "${browseViewModel.currentUser!!.sName} ",
                                onValueChange = { sName = it },
                                colors = TextFieldDefaults.textFieldColors(
                                    backgroundColor = Color.Transparent,
                                    textColor = Purple200
                                )
                            )
                        }
                        Spacer(modifier = Modifier.size(10.dp))

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 4.dp, end = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Password:",
                                modifier = Modifier.width(100.dp),
                                color = Purple200
                            )
                            TextField(
                                value = "${browseViewModel.currentUser!!.sPass} ",
                                onValueChange = { sPass = it },
                                colors = TextFieldDefaults.textFieldColors(
                                    backgroundColor = Color.Transparent,
                                    textColor = Purple200
                                )
                            )
                        }
                        Spacer(modifier = Modifier.size(10.dp))

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 4.dp, end = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Confirm Password:",
                                modifier = Modifier.width(100.dp),
                                color = Purple200
                            )
                            TextField(
                                value = sPassConfirm,
                                onValueChange = { sPassConfirm = it },
                                colors = TextFieldDefaults.textFieldColors(
                                    backgroundColor = Color.Transparent,
                                    textColor = Purple200
                                )
                            )
                        }
                        Spacer(modifier = Modifier.size(10.dp))

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 4.dp, end = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Email:",
                                modifier = Modifier.width(100.dp),
                                color = Purple200
                            )
                            TextField(
                                value = "${browseViewModel.currentUser!!.sEmail} ",
                                onValueChange = { sEmail = it },
                                colors = TextFieldDefaults.textFieldColors(
                                    backgroundColor = Color.Transparent,
                                    textColor = Purple200
                                )
                            )
                        }
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
        },
    bottomBar = { BottomBar(navController) }
    )
}

//fun checkCredentials(sName: String ,sPass: String, sPassConfirm: String): Boolean {
//    return sName.isNotEmpty() && sPass.isNotEmpty() && sPassConfirm.isNotEmpty()
//}

@coil.annotation.ExperimentalCoilApi
@Composable
fun ChangeImage() {
    var imageUri by rememberSaveable { mutableStateOf("") }
    val painter = rememberImagePainter(
        imageUri.ifEmpty {
            R.drawable.selfie
        }
    )
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let { imageUri = it.toString() }
    }
    Column(modifier = Modifier
        .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            shape = CircleShape,
            modifier = Modifier
                .padding(8.dp)
                .size(100.dp)
        ) {
            Image(
                painter = painter,
                contentDescription = "Selected image",
                modifier = Modifier
                    .wrapContentSize()
                    .clickable { launcher.launch("image/*") },
                contentScale = ContentScale.Crop
            )

        }
    }
    Text(text = "Change Profile Picture", color = Purple200)
}

