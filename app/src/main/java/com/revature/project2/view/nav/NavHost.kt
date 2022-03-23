package com.revature.project2.view.nav

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.revature.project2.view.BrowseItemsScreen


@Composable
fun startNav(){
    val navController = rememberNavController()

    NavHost(navController = navController,
        startDestination = NavScreens.BrowseItemsScreen.route){
        composable(NavScreens.BrowseItemsScreen.route){
            BrowseItemsScreen(navController)
        }
    }
}