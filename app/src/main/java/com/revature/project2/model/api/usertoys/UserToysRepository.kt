package com.revature.project2.model.api.usertoys

import android.util.Log
import com.revature.project2.model.api.ToysApiService
import com.revature.project2.model.api.alltoys.RequestAllToys
import com.revature.project2.model.api.alltoys.ToyItem


class UserToysRepository(val toyServiceApi: ToysApiService) {

    /**
     * Sealed class to determine the result of the api call
     */
    sealed class Result{
        object Loading:Result()
        data class Success(val toyList:List<ToyItem>):Result()
        data class Failure(val throwable:Throwable):Result()
    }

    /**
     * Coroutine function for loading from server
     *
     * Returns either:
     * Successful Result containing the list of toys
     *
     * Failed Result containing and exception
     *
     */
    suspend fun getUserToys():Result{

        return try {

            val toyList = toyServiceApi.getUserToys(
                RequestUserToys(
                    "posterid",
                    "0")
            ).results

            Log.d("UserToys", "Success " + toyList.size)
            Result.Success(toyList)

        } catch (e:Exception){
            Log.d("UserToys", "Failed $e")
            Result.Failure(e)

        }
    }

}