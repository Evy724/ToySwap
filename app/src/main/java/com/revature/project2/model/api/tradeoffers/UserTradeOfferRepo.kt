package com.revature.project2.model.api.tradeoffers

import android.util.Log
import com.revature.project2.model.api.ToysApiService

class UserTradeOfferRepo(val toyServiceApi: ToysApiService) {

    sealed class Result{
        object Loading:Result()
        data class Success(val offerList:List<TradeOffer>):Result()
        data class Failure(val throwable:Throwable):Result()
    }

    suspend fun fetchUserOffers(nUser:Int):Result{

        return try {

            val offerList = toyServiceApi.getUserOffers(
                RequestUserOffers(
                "user_id",
                nUser)
            ).results

            Log.d("ToyList", "Success " + offerList.size)
            Result.Success(offerList)

        } catch (e:Exception){
            Log.d("ToyList", "Failed")
            Result.Failure(e)

        }
    }

}