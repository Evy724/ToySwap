package com.revature.project2.model.api.finalizetradeoffer

import com.google.gson.annotations.SerializedName
import com.revature.project2.model.api.alltoys.ToyItem

data class FinalizeTradeOffer_Request_API (

    @SerializedName("toyOtherUserIsTrading")
    var result1: ToyItem,

    @SerializedName("toyUserIsTrading")
    var result2: ToyItem,

    @SerializedName("msg_input")
    val msg_input: String,
)