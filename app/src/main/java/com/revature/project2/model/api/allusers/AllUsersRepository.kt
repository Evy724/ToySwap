package com.revature.project2.model.api.allusers

import android.util.Log
import com.revature.project2.model.api.ToysApiService
import java.lang.Exception

class AllUsersRepository(val apiService: ToysApiService) {

    sealed class Result{
        object Loading:Result()
        data class Success(val userList:List<User>):Result()
        data class Failure(val throwable:Throwable):Result()
    }

    suspend fun fetchAllUsers():Result{
        return try {
            val userList =
                apiService.getUsers(
                    AllUsersRequest(
                        "users",
                        "all"
                    )
                ).results
            Log.d("User Repository", "Users Loaded Successfully")
            Result.Success(userList)

        }catch (e:Exception){
            Log.d("User Repository", "Users Loading Failed")
            Result.Failure(e)

        }
    }
}