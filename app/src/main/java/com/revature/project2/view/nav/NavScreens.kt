package com.revature.project2.view.nav

/**
 * List of screens in the app and their route (function name as string)
 */
sealed class NavScreens(val route:String) {

    object BrowseItemsScreen:NavScreens("BrowseItemsScreen")
}