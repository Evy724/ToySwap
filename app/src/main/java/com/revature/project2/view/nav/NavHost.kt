package com.revature.project2.view.nav

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.revature.project2.view.BrowseItemsScreen
import com.revature.project2.viewmodel.AllToysViewModel

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
fun startNav(){
    val navController = rememberNavController()

    val allToysViewModel = AllToysViewModel()

    NavHost(navController = navController,
        startDestination = NavScreens.BrowseItemsScreen.route){

        composable(NavScreens.BrowseItemsScreen.route){
            BrowseItemsScreen(navController,allToysViewModel)
        }

        //Add composable navigation here
    }
}