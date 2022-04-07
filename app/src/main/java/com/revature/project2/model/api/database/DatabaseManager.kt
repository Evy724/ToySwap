package com.revature.project2.model.api.database

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import com.revature.project2.model.api.RetrofitHelper
import com.revature.project2.model.api.alltoys.ToyItem
import com.revature.project2.model.api.usertoys.UserToysRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class DatabaseManager(app:Application) {

    private lateinit var userToyRepo:UserToyRoomRepo
    init {
        runBlocking {
            launch {
                withContext(Dispatchers.IO) {
                    userToyRepo = UserToyRoomRepo(app)
                }
            }
        }
    }

    private val netManager =
        app.applicationContext.getSystemService(
            Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    fun fetchAllUserToys():List<ToyItem>{

        var userToys:List<ToyItem> = listOf()

        if (netManager.getNetworkCapabilities(
                netManager.activeNetwork) != null){

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
                        }
                        //If failed, log and continue
                        is UserToysRepository.Result.Failure-> {
                            Log.d("ViewModel", "Load Failed")
                            userToys = userToyRepo.readAllUserToys
                        }
                    }

                }
            }
        } else {
            runBlocking {
                launch {
                    userToys = userToyRepo.readAllUserToys
                }
            }
        }
        return userToys
    }
}