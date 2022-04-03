package com.revature.project2.viewmodel

import android.util.Log
import android.util.MutableBoolean
import androidx.compose.runtime.mutableStateOf
import com.revature.project2.model.api.RetrofitHelper
import com.revature.project2.model.api.allusers.AllUsersRepository
import com.revature.project2.model.api.allusers.User
import com.revature.project2.model.api.login.LoginRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.*

class LoginViewModel():ViewModel() {

    //get our service with retrofit helper
    val apiService = RetrofitHelper.getAllToysService()

    //for retrieving a list of all users
    private lateinit var userRepo:AllUsersRepository

    //var allUsers:LiveData<List<User>> = MutableLiveData()
    var allUsers:List<User> by mutableStateOf(listOf())

    //For sending user info to server
    val loginRequestLiveData = MutableLiveData<Boolean>()


    init {
        Log.d("Login ViewModel","Initialization")
        viewModelScope.launch(Dispatchers.IO) {
            getAllUsers()
        }
    }

    //Logic Functions

    //Checks the passed in user against the list of users
    fun existingUserCheck(sName:String,sPass:String):User?{
        var user:User? = null

        Log.d("Login Screen", "$sName - $sPass")

        allUsers.forEach {
            Log.d("Login Screen", "User: ${it.sName} - ${it.sPass}")
            if (sName.equals(it.sName) && sPass.equals(it.sPass)) {
                user = it

            }
        }

        return user
    }

    // API Loading Functions

    private suspend fun getAllUsers(){

        userRepo = AllUsersRepository(apiService)
        when (val response = userRepo.fetchAllUsers()){

            is AllUsersRepository.Result.Success->{

                Log.d("Login ViewModel","Load Users Successful")
                allUsers = response.userList
            }

            is AllUsersRepository.Result.Failure->{

                Log.d("Login ViewModel","Load Users Failed")
            }
        }


    }

    fun login(username:String, password:String) {

        viewModelScope.launch(Dispatchers.IO) {
            Log.d("Login ViewModel","Log in API Call")

            try {

                val responseService =
                    apiService.getLoginAuth(
                        (LoginRequest(
                            sUsername = username,
                            sPassword = password)))

                if (responseService.isSuccessful){
                    responseService.body()?.let { token ->
                        Log.d("Login Success", "Response Token: $token")
                        //requestToken.value = true
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