package com.revature.project2.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.revature.project2.model.api.RetrofitHelper
import com.revature.project2.model.api.alltoys.AllToysRepository
import com.revature.project2.model.api.alltoys.ToyItem
import kotlinx.coroutines.launch

/**
 * View Model for use when using all toys in the server
 */
class AllToysViewModel(): ViewModel() {

    //Get the service that handles loading of all toys
    private val toyService = RetrofitHelper.getAllToysService()

    //Repository of All Toys api
    private lateinit var toyRepo: AllToysRepository

    //List of the toy items loaded in
    var allToys:List<ToyItem> by mutableStateOf(listOf())

    //Initialize by calling function to load toys
    init {
        getAllToys()

    }

    /**
     * Function that loads all toys in from API
     */
    private fun getAllToys() {

        //set or repository, created with the Service Interface
        toyRepo = AllToysRepository((toyService))

        //Corourtine launch scope
        viewModelScope.launch {

            //call the loading function from the repository and save to a variable
            var response = toyRepo.fetchAllToys()

            //Check for type of response
            when (response) {

                //When the response was Successful, Log it and store the retrieved
                //toys into our toy list
                is AllToysRepository.Result.Success-> {
                    Log.d("ViewModel", "Load Successful")
                    allToys = response.toyList
                }
                //If failed, log and continue
                is AllToysRepository.Result.Failure-> {
                    Log.d("ViewModel", "Load Failed")
                }
            }
        }
    }
}