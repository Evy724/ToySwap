package com.revature.project2.view.nav

/**
 * List of screens in the app and their route (function name as string)
 */
sealed class NavScreens(val route:String) {

    object LoginScreen:NavScreens("LoginScreen")
    object BrowseItemsScreen:NavScreens("BrowseItemsScreen")
    object PostedItemListScreen:NavScreens("PostedItemListScreen")
    object RegisterScreen:NavScreens("RegisterScreen")
    object TradeProposalScreen:NavScreens("TradeProposalScreen")
    object FinalizeTradeScreen:NavScreens("FinalizeTradeScreen")
}