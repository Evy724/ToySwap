package com.revature.project2.model

import android.app.Application
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModelProvider
import com.revature.project2.MainActivity
import com.revature.project2.model.api.RetrofitHelper
import com.revature.project2.model.api.alltoys.ToyItem
import com.revature.project2.model.api.allusers.AllUsersRepository
import com.revature.project2.model.api.allusers.User
import com.revature.project2.viewmodel.LoginViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object AppManager {

    private lateinit var app:Application
    private lateinit var activity:MainActivity
    private val apiService = RetrofitHelper.getAllToysService()

    //lateinit var allToys:List<ToyItem>
    var users by mutableStateOf(listOf<User>())
//    var users:List<User> = listOf()

    lateinit var currentUser:User

    init {
        CoroutineScope(Dispatchers.IO).launch {
            loadUsers()
        }
    }
    fun setApplication(application:Application){
        app=application
    }
    fun setActivity(act: MainActivity){
        activity = act
    }

    fun getUserNameByID(id:Int):String?{
        users.forEach { user->
            if (user.nUserId == id) return user.sName
        }
        return null
    }


    private suspend fun loadUsers(){
        var userRepo = AllUsersRepository(apiService)

        when (val response = userRepo.fetchAllUsers()){

            is AllUsersRepository.Result.Success->{

                Log.d("Login ViewModel","Load Users Successful")
                users = response.userList
                var loginVM =
                    ViewModelProvider(activity)
                        .get(LoginViewModel::class.java)
                loginVM.bUsersLoaded.value = true
            }

            is AllUsersRepository.Result.Failure->{

                Log.d("Login ViewModel","Load Users Failed")
            }
        }
    }
}