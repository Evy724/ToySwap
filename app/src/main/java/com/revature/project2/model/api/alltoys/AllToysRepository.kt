package com.revature.project2.model.api.alltoys

import android.util.Log
import com.revature.project2.model.api.ToysApiService

/**
 * Repository for retrieving all toys from server
 */
class AllToysRepository(val toyServiceApi: ToysApiService) {

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
    suspend fun fetchAllToys():Result{

        return try {

            val toyList = toyServiceApi.getToys(RequestAllToys(
                "Toys",
                "all")).results

            Log.d("ToyList", "Success " + toyList.size)
            Result.Success(toyList)

        } catch (e:Exception){
            Log.d("ToyList", "Failed")
            Result.Failure(e)

        }
    }

}