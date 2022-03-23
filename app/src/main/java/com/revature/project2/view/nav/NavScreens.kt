package com.revature.project2.view.nav


sealed class NavScreens(val route:String) {

    object BrowseItemsScreen:NavScreens("BrowseItemsScreen")
}