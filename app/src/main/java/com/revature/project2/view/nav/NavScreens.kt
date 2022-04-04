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
    object ViewItemScreen:NavScreens("ViewItemScreen")
    object AcceptTradeScreen:NavScreens("AcceptTradeScreen")
    object NewToyPostScreen:NavScreens("NewToyPostScreen")
    object ViewPostedToyScreen:NavScreens("NewToyPostScreen")
    object MyProfileScreen:NavScreens("MyProfileScreen")
    object ProfileScreen:NavScreens("ProfileScreen")
    object EditProfileScreen:NavScreens("EditProfileScreen")

}