package com.revature.project2.model.api.alltoys

import android.util.Log
import com.revature.project2.model.api.ToysApiService
import com.revature.project2.model.api.finalizetradeoffer.SendTrade_Request_API

//
//import java.lang.Exception
//
//class SendTradeRepository (val toyServiceApi: ToysApiService){
//    sealed class Result{
//        object Loading:Result()
//        data class Response(val trade_msg:Trade_msg):Result()
//        data class Failure(val throwable:Throwable):Result()
//    }
//    suspend fun fetchSendTradeResponse(): SendTradeRepository.Result {
//
//        return try {
//            val msg=toyServiceApi.getSendTradeOffer_msg(SendTrade_Request_API(
//                "tradeRequestID",
//                "SendTrade_Request_msg")
//            )
//            Result.Response(msg)
//        }catch (ex:Exception)
//        {
//            Result.Failure(ex)
//        }
//    }
//}