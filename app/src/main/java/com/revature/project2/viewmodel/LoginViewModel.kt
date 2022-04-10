package com.revature.project2.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import android.util.MutableBoolean
import android.widget.Toast
import androidx.compose.runtime.MutableState
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
import androidx.navigation.NavController
import com.revature.project2.MainActivity
import com.revature.project2.model.AppManager
import com.revature.project2.model.DataStore
import com.revature.project2.view.nav.NavScreens

class LoginViewModel():ViewModel() {

    //get our service with retrofit helper
    val apiService = RetrofitHelper.getAllToysService()

    //for retrieving a list of all users
    private lateinit var userRepo:AllUsersRepository

    //var allUsers:LiveData<List<User>> = MutableLiveData()
    //var allUsers:List<User> by mutableStateOf(listOf())

    //For sending user info to server
    val loginRequestLiveData = MutableLiveData<Boolean>()

    var bUsersLoaded = mutableStateOf<Boolean>(false)


//    init {
//        Log.d("Login ViewModel","Initialization")
//        //allUsers = AppManager.users
//    }

    fun setCurrentUser(user:User){
        AppManager.currentUser = user
    }

    //Checks the passed in user against the list of users
    fun existingUserCheck(sName:String,sPass:String):User?{
        var user:User? = null

        Log.d("Login Screen", "$sName - $sPass")

        AppManager.users.forEach {
            Log.d("Login Screen", "User: ${it.sName} - ${it.sPass}")
            if (sName.equals(it.sUserName) && sPass.equals(it.sPass)) {
                user = it
            }
        }

        return user
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

    fun loginWithNet(sName: String,sPass: String, context:Context, navController: NavController) : Boolean{

        //Check if the user exists in our server
        var user: User? = existingUserCheck(sName, sPass)
        if (user != null) {

            //Set Current user in AppManager
            setCurrentUser(user)

            //Set up user in Browse VM
            val browseVM =
                ViewModelProvider(context as MainActivity).get(AllToysViewModel::class.java)
            browseVM.currentUser = user
            Log.d("Login Screen", "Current User Set")

            //If it does, log in with that user
            login(sName, sPass)

            //Save to dataStore
            viewModelScope.launch {
                val dataStore = DataStore(context)
                dataStore.saveUsername(sName)
                dataStore.savePassword(sPass)
            }
            navController.navigate(NavScreens.BrowseItemsScreen.route)

            return true
        } else {
            return false
        }

    }
}