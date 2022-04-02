package com.revature.project2.model.api.finalizetradeoffer

import com.google.gson.annotations.SerializedName
import com.revature.project2.model.api.alltoys.ToyItem

data class FinalizeTradeOffer_Request_API (


    @SerializedName("tradeRequestID")
    val tradeRequestID: Int,

    @SerializedName("acceptOrDeclineTrade")
    val acceptOrDeclineTrade: Boolean

)