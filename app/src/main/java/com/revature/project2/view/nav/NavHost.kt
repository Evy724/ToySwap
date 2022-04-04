package com.revature.project2.view.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavArgument
import androidx.navigation.NavDeepLinkSaveStateControl
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.revature.project2.MainActivity
import androidx.navigation.navArgument
import com.revature.project2.ui.jc.tradeFinalizeScreen
import com.revature.project2.ui.jc.tradeProposalScreen
import com.revature.project2.ui.AcceptTradeScreen
import com.revature.project2.ui.ViewItemScreen
import com.revature.project2.view.composables.*
import com.revature.project2.view.composables.Register
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

    //val allToysViewModel by lazy { AllToysViewModel() }
    val userToysViewModel by lazy { UserToysViewModel()}
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
        composable(NavScreens.TradeProposalScreen.route+"/{toyid}/{toyImage}",
        arguments = listOf(
            navArgument("toyid"){
                type= NavType.IntType
            },
            navArgument("toyImage"){
                type= NavType.StringType
            }
        )
        ){
            var toyid=it.arguments?.getInt("toyid")?:0
            var toyImage=it.arguments?.getString("toyImage")?:""

            tradeProposalScreen(navController,userToysViewModel,toyid,toyImage)
        }

        composable(NavScreens.FinalizeTradeScreen.route+"/{myToyID}/{theirToyID}/{myImage}/{theirImage}",
        arguments = listOf(
            navArgument("myToyID"){
                type= NavType.IntType
            },
            navArgument("theirToyID"){
                type= NavType.IntType
            },
            navArgument("myImage")
            {
                type= NavType.StringType
            },
            navArgument("theirImage")
            {
                type= NavType.StringType
            }
        )
        ){
            var myToyID=it.arguments?.getInt("myToyID")?:0
            var theirToyID=it.arguments?.getInt("theirToyID")?:0
            var myImage=it.arguments?.getString("myImage")?:""
            var theirImage=it.arguments?.getString("theirImage")?:""

            tradeFinalizeScreen(
                navController = navController,
                myToyID = myToyID ,
                theirToyID =theirToyID ,
                myImage = myImage,
                theirImage = theirImage
            )

        }

        // View Item Screen
        composable(NavScreens.ViewItemScreen.route){ViewItemScreen(navController = navController)}

        composable(NavScreens.AcceptTradeScreen.route){
            AcceptTradeScreen(navController = navController)
        }
        composable(NavScreens.ProfileScreen.route){
            MyProfileScreen(navController = navController)
        }
    }
}