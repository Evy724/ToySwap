package com.revature.project2.model

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.revature.project2.model.api.RetrofitHelper
import com.revature.project2.model.api.alltoys.ToyItem
import com.revature.project2.model.api.usertoys.UserToysRepository
import com.revature.project2.model.database.UserToyDatabase
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

object DataManager {

    var userToys:List<ToyItem> by mutableStateOf(listOf())

    fun loadUserToys(context: Context){
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        //Load user toys based on internet connection
        if(connectivityManager.getNetworkCapabilities(
                connectivityManager.activeNetwork) != null){
            getUserToys(context)
        } else {
            userToys = UserToyDatabase.getDatabase(context = context).userToyDao().fetchAllUserToys().value!!
        }

    }
    private fun getUserToys(context: Context){

        val toyService = RetrofitHelper.getAllToysService()

        //set or repository, created with the Service Interface
        var toyRepo = UserToysRepository((toyService))

        //Corourtine launch scope
        runBlocking {
            launch {
                //Check for type of response
                when (val response = toyRepo.getUserToys()) {

                    //When the response was Successful, Log it and store the retrieved
                    //toys into our toy list
                    is UserToysRepository.Result.Success-> {
                        Log.d("ViewModel", "Load Successful")
                        userToys = response.toyList

                        //delete current room and update
                        UserToyDatabase.getDatabase(context).userToyDao().deleteUserToys()
                        for (userToy in userToys) {
                            UserToyDatabase.getDatabase(context).userToyDao().insertUserToy(userToy)

                        }
                    }
                    //If failed, log and continue
                    is UserToysRepository.Result.Failure-> {
                        Log.d("ViewModel", "Load Failed")
                        userToys = UserToyDatabase.getDatabase(context = context).userToyDao().fetchAllUserToys().value!!
                    }
                }

            }
        }
//        viewModelScope.launch {
//
//            //call the loading function from the repository and save to a variable
//
//            //Check for type of response
//            when (val response = toyRepo.getUserToys()) {
//
//                //When the response was Successful, Log it and store the retrieved
//                //toys into our toy list
//                is UserToysRepository.Result.Success-> {
//                    Log.d("ViewModel", "Load Successful")
//                    userToys = response.toyList
//                }
//                //If failed, log and continue
//                is UserToysRepository.Result.Failure-> {
//                    Log.d("ViewModel", "Load Failed")
//                }
//            }
//        }
    }
}