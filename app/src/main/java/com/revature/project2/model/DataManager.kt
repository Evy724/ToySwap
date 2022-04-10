package com.revature.project2.model

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.revature.project2.model.api.RetrofitHelper
import com.revature.project2.model.api.alltoys.ToyItem
import com.revature.project2.model.api.database.UserToyRoomRepo
import com.revature.project2.model.api.usertoys.UserToysRepository
import com.revature.project2.model.database.UserToyDatabase
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

object DataManager {

    var userToys:List<ToyItem> by mutableStateOf(listOf())

    private val userToyRepo: UserToyRoomRepo = UserToyRoomRepo(AppManager.app)

    fun checkNetAccess(context: Context):Boolean {

        //Create Connectivity Manager with the activity
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val network = connectivityManager.activeNetwork ?: return false

        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

        return when {
            // Indicates this network uses a Wi-Fi transport,
            // or WiFi has network connectivity
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true

            // Indicates this network uses a Cellular transport. or
            // Cellular has network connectivity
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

            // else return false
            else -> false
        }
    }

    //Load user toys based on internet connection
    suspend fun loadUserToys(context: Context){

        //Check for net access
        if(checkNetAccess(context)){

            //If were connected, grab toys from api
            getUserToys(context)
        } else {

            //if not load them in
            val toys = userToyRepo.readAllUserToys
            if (toys != null){
                userToys = toys
            }
        }

    }
     private suspend fun getUserToys(context: Context){

        val toyService = RetrofitHelper.getAllToysService()

        //set or repository, created with the Service Interface
        var toyRepo = UserToysRepository((toyService))

        //Check for type of response
        when (val response = toyRepo.getUserToys()) {

            //When the response was Successful, Log it and store the retrieved
            //toys into our toy list
            is UserToysRepository.Result.Success-> {
                Log.d("ViewModel", "Load Successful")
                userToys = response.toyList

                //delete current room and update
                userToyRepo.deleteUserToys()
                //UserToyDatabase.getDatabase(context).userToyDao().deleteUserToys()
                for (userToy in userToys) {
                    userToyRepo.insertCustomer(userToy)
                    //UserToyDatabase.getDatabase(context).userToyDao().insertUserToy(userToy)
                }
                var toys = userToyRepo.readAllUserToys
                Log.d("User Toy Room","Room Size : ${toys?.size}")
            }
            //If failed, log and load from database
            is UserToysRepository.Result.Failure-> {

                Log.d("ViewModel", "Load Failed")

                val toys = userToyRepo.readAllUserToys
                if (toys != null){
                    userToys = toys
                }
            }
        }
    }
}