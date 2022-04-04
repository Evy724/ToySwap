package com.revature.project2.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.*
import com.revature.project2.model.api.RetrofitHelper
import com.revature.project2.model.api.alltoys.AllToysRepository
import com.revature.project2.model.api.alltoys.ToyItem
import com.revature.project2.model.api.allusers.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * View Model for use when using all toys in the server
 */
class AllToysViewModel(): ViewModel()
{

    //Get the service that handles loading of all toys
    private val toyService = RetrofitHelper.getAllToysService()

    //Repository of All Toys api
    private lateinit var toyRepo: AllToysRepository

    //List of the toy items loaded in
    var allToys:List<ToyItem> by  mutableStateOf(listOf())

    var currentUser: User? = null

    //Initialize by calling function to load toys
    init {

        Log.d("Browse ViewModel","Initialization")
        viewModelScope.launch(Dispatchers.IO) {
            getAllToys()
        }
    }



    /**
     * Function that loads all toys in from API
     */
    private suspend fun getAllToys() {


        //set or repository, created with the Service Interface
        toyRepo = AllToysRepository((toyService))
        Log.d("Browse ViewModel","Get All Toys")


        //call the loading function from the repository and save to a variable

        //Check for type of response
        when (val response = toyRepo.fetchAllToys()) {

            //When the response was Successful, Log it and store the retrieved
            //toys into our toy list
            is AllToysRepository.Result.Success-> {

                Log.d("ViewModel", "Load Successful")

                //allToys.addAll(response.toyList)
                allToys = removeUserToys(response.toyList)
            }
            //If failed, log and continue
            is AllToysRepository.Result.Failure-> {

                Log.d("ViewModel", "Load Failed")
            }
        }

    }
    private fun removeUserToys(original:List<ToyItem>):List<ToyItem>{

        var list = ArrayList<ToyItem>()

        Log.d("Browse ViewModel","Remove User Toys")

        if (currentUser == null) return original

        original.forEach {
            if(it.posterId != currentUser!!.nUserId){
                list.add(it)
            }
        }
        return list

    }
}
