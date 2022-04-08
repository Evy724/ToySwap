package com.revature.project2

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.revature.project2.model.AppManager
import com.revature.project2.ui.theme.Project2Theme
import com.revature.project2.view.nav.StartNav
import com.revature.project2.viewmodel.SplashViewModel

//import com.revature.project2.view.nav.StartNav



class MainActivity : ComponentActivity() {

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        val window = this.window
        window.statusBarColor = this.resources.getColor(R.color.teal_200)

        super.onCreate(savedInstanceState)

        //val app = this.application
        val appManager = AppManager
        appManager.setApplication(this.application)
        appManager.setActivity(this)

        //Constructor for the Splash screen
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.isLoading.value
            }
        }

        setContent {
            Project2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Log.d("MainActivity","Main activity Set Content")

//                    Start our app's navigation
                    StartNav(this)

                }
            }
        }
    }

}

