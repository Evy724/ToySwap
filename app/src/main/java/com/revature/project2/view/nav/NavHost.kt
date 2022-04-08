package com.revature.project2.view.nav

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.revature.project2.MainActivity
import com.revature.project2.ui.AcceptTradeScreen
import com.revature.project2.ui.ViewItemScreen
import com.revature.project2.ui.jc.tradeFinalizeScreen
import com.revature.project2.view.composables.*

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

    //val allToysViewModel by lazy { AllToysViewModel() }
    //val userToysViewModel by lazy { UserToysViewModel()}
    //val loginViewModel by lazy { LoginViewModel() }

    NavHost(navController = navController,
        startDestination = NavScreens.LoginScreen.route){

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
            tradeProposalScreen(navController/*,userToysViewModel*/)
        }

        composable(NavScreens.FinalizeTradeScreen.route
        ){
            tradeFinalizeScreen(
                navController = navController)
        }
        composable(NavScreens.EditProfileScreen.route) {
            EditProfileScreen(navController = navController)
        }

        // View Item Screen
        composable(NavScreens.ViewItemScreen.route){
            ViewItemScreen(navController = navController)
        }

        composable(NavScreens.AcceptTradeScreen.route){
            AcceptTradeScreen(navController = navController)
        }
        composable(NavScreens.ProfileScreen.route){
            MyProfileScreen(navController = navController)
        }
        composable (NavScreens.ViewPostedToyScreen.route){
            viewPostedToyScreen(navController)
        }
        composable(NavScreens.NewToyPostScreen.route){
            newToyPostScreen(navController = navController)
        }
    }
}