package com.revature.project2.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.revature.project2.model.api.RetrofitHelper
import com.revature.project2.model.api.login.LoginRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class LoginViewModel():ViewModel() {

    private val loginRequestLiveData = MutableLiveData<Boolean>()
    var requestToken = mutableStateOf(value=false)

    fun login(username:String, password:String){
        viewModelScope.launch(Dispatchers.IO) {

            try {
                val authService = RetrofitHelper.getAllToysService()

                val responseService =
                    authService.getLoginAuth(
                        (LoginRequest(
                            sUsername = username,
                            sPassword = password)))

                if (responseService.isSuccessful){
                    responseService.body()?.let { token ->
                        Log.d("Login Success", "Response Token: $token")
                        requestToken.value = true
                    }
                } else {
                    responseService.errorBody()?.let { error ->
                        Log.d("Login Error", "Response: $error")
                        error.close()
                    }
                }
                loginRequestLiveData.postValue(responseService.isSuccessful)

            } catch (e:Exception) {
                Log.d("Network Exception","Exception: $e")

            }
        }
    }
}