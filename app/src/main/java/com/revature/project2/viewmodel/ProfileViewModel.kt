package com.revature.project2.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.revature.project2.model.api.RetrofitHelper
import com.revature.project2.model.api.user.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileViewModel: ViewModel() {

    private val getProfileRequestLiveData = MutableLiveData<Boolean>()

    fun getProfile(profile: User) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val profileService = RetrofitHelper.getProfileService()
                val responseService = profileService.getProfile(profile)

                if(responseService.isSuccessful) {
                    responseService.body()?.let { Profile ->
                        Log.d("Profile", "Response Profile $Profile")
                    }
                } else {
                    responseService.errorBody()?.let { error ->
                        Log.d("Profile", "Response Profile $error")
                        error.close()
                    }
                }
                getProfileRequestLiveData.postValue(responseService.isSuccessful)
            } catch (e: Exception) {
                Log.d("Profile", "Exception in Networking $e")
            }
        }
    }
    fun getFirstName(profile: User) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val profileService = RetrofitHelper.getProfileService()
                val responseService = profileService.getProfile(profile)

                if (responseService.isSuccessful) {
                    responseService.body()?.let { first_name ->
                        Log.d("first_name", "Response first_name $first_name")
                    }
                } else {
                    responseService.errorBody()?.let { error ->
                        Log.d("first_name", "Response first_name $error")
                        error.close()
                    }
                }
                getProfileRequestLiveData.postValue(responseService.isSuccessful)
            } catch (e: Exception) {
                Log.d("first_name", "Exception in Networking $e")
            }
        }
    }
}