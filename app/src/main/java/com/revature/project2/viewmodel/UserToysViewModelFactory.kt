package com.revature.project2.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.revature.project2.model.api.allusers.User
import java.lang.IllegalArgumentException

//class UserToysViewModelFactory(private val user: User, private val app:Application):ViewModelProvider.Factory {
//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        if(modelClass.isAssignableFrom(UserToysViewModel::class.java)){
//            return UserToysViewModel(user,app) as T
//        }
//        throw IllegalArgumentException("Unknown Viewmodel Class")
//    }
//}