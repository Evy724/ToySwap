package com.revature.project2.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.revature.project2.model.api.RetrofitHelper
import com.revature.project2.model.api.ToysApiService
import com.revature.project2.model.api.alltoys.ToyItem
import com.revature.project2.model.api.alltoys.Trade_msg
import com.revature.project2.model.api.finalizetradeoffer.SendTrade_Request_API
import com.revature.project2.model.api.finalizetradeoffer.SendTrade_Response_API
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
var msgFromTVM:String=""
class TradeViewModel: ViewModel(){
    var theirToy:ToyItem?=null

    fun getSendTrade_msg(_tradeRequestID:String,_tradeRequest_msg:String){
        viewModelScope.launch(Dispatchers.IO) {
           try {
               val sendTradeOfferService=RetrofitHelper.getAllToysService()
               val responseService=sendTradeOfferService.getSendTradeOffer_msg(SendTrade_Request_API(tradeRequestID =_tradeRequestID,
                   SendTrade_Request_msg =_tradeRequest_msg ))

               if (responseService.isSuccessful)
               {
                   responseService.body()?.let {

                       msgFromTVM=it.finalizeTradeOffer_Request_success

                   }

               }
               else
               {
                   responseService.errorBody()?.let { error->
                       error.close()
                   }
               }

           }catch (e:Exception)
           {
               Log.d("Exception","${e.message}")
           }
        }
    }
}
