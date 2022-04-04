package com.revature.project2.model.api.finalizetradeoffer

import com.google.gson.annotations.SerializedName

data class SendTrade_Request_API (


    @SerializedName("tradeRequestID")
    val tradeRequestID: String,

    @SerializedName("SendTrade_Request_msg")
    val SendTrade_Request_msg: String

)