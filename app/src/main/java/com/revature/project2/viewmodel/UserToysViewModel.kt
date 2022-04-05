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
import com.revature.project2.model.api.allusers.User
import com.revature.project2.model.api.tradeoffers.TradeOffer
import com.revature.project2.model.api.tradeoffers.UserTradeOfferRepo
import com.revature.project2.model.api.usertoys.UserToysRepository
import kotlinx.coroutines.launch

class UserToysViewModel(val user:User): ViewModel() {

    private val toyService = RetrofitHelper.getAllToysService()

    private lateinit var toyRepo:UserToysRepository
    private lateinit var userOfferRepo:UserTradeOfferRepo

    var userToys:List<ToyItem> by mutableStateOf(listOf())
    var userTradeOffers:List<TradeOffer> by mutableStateOf(listOf())

    //var user: User? = null

    init {
        getUserToys()
        if(user!= null)
            getUserOffers()
    }

    fun getUserOffers(){

        //set or repository, created with the Service Interface
        userOfferRepo = UserTradeOfferRepo((toyService))

        //Corourtine launch scope
        viewModelScope.launch {

            //call the loading function from the repository and save to a variable


            //Check for type of response
            when (val response = userOfferRepo.fetchUserOffers(user!!.nUserId)) {

                //When the response was Successful, Log it and store the retrieved
                //toys into our toy list
                is UserTradeOfferRepo.Result.Success-> {
                    Log.d("ViewModel", "Load Successful")
                    userTradeOffers = response.offerList
                }
                //If failed, log and continue
                is UserTradeOfferRepo.Result.Failure-> {
                    Log.d("ViewModel", "Load Failed")
                }
            }
        }
    }

    private fun getUserToys(){

        //set or repository, created with the Service Interface
        toyRepo = UserToysRepository((toyService))

        //Corourtine launch scope
        viewModelScope.launch {

            //call the loading function from the repository and save to a variable

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
                }
            }
        }
    }

}