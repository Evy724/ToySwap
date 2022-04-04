package com.revature.project2.model.api.finalizetradeoffer

import com.google.gson.annotations.SerializedName

data class SendTrade_Response_API (


    @SerializedName("SendTradeOffer_Request_success")
    val finalizeTradeOffer_Request_success: String
)
