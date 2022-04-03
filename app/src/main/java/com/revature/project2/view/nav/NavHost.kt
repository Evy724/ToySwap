package com.revature.project2.view.nav

import android.app.Application
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.revature.project2.MainActivity
import com.revature.project2.model.api.alltoys.ToyItem
import com.revature.project2.ui.jc.tradeFinalizeScreen
import com.revature.project2.ui.jc.tradeProposalScreen
import com.revature.project2.ui.AcceptTradeScreen
import com.revature.project2.ui.ViewItemScreen
import com.revature.project2.view.composables.BrowseItemsScreen
import com.revature.project2.view.composables.Login
import com.revature.project2.view.composables.PostedItemsScreen
import com.revature.project2.view.composables.Register
import com.revature.project2.viewmodel.AllToysViewModel
import com.revature.project2.viewmodel.LoginViewModel
import com.revature.project2.viewmodel.UserToysViewModel

/**
 * Starts navigation for the app
 *
 * Contains the different composable screens throughout the app
 *
 * View models should be created here and sent to the composables via params
 *
 * note: large data objects are not passed via appending of route,
 * only primitive types should be passed this way
 * i.e. route + /{object} not possible
 * route + /{Int} possible
 */
@Composable
fun StartNav(app:MainActivity){
    val navController = rememberNavController()
    Log.d("Nav","Start Nav called")

    //val allToysViewModel by lazy { AllToysViewModel() }
    //val userToysViewModel by lazy { UserToysViewModel()}
    //val loginViewModel by lazy { LoginViewModel() }

    NavHost(navController = navController,
        startDestination = NavScreens.LoginScreen.route){

        Log.d("Nav","NavHost")

        composable(NavScreens.BrowseItemsScreen.route){
            BrowseItemsScreen(navController)
        }

        composable(NavScreens.PostedItemListScreen.route){
            PostedItemsScreen(navController)
        }
        
        composable(NavScreens.LoginScreen.route){
            Login(navController = navController)
        }
        
        composable(NavScreens.RegisterScreen.route){
            Register(navController = navController)
        }
        composable(NavScreens.TradeProposalScreen.route){
            tradeProposalScreen(navController = navController,
                ToyItem(0,"tom","tom:",0,"")
            )
        }
        composable(NavScreens.FinalizeTradeScreen.route){
            tradeFinalizeScreen(navController)
        }

        //Add composable navigation here

        composable(NavScreens.ViewItemScreen.route) {
            ViewItemScreen()
        }

        composable(NavScreens.AcceptTradeScreen.route){
            AcceptTradeScreen()
        }
    }
}