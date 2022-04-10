package com.revature.project2.model.api.tradeoffers

import com.google.gson.annotations.SerializedName

data class TradeOffer(

    @SerializedName("offer_id")
    val id:Int,

    @SerializedName("user_toy")
    val nUserToyId:Int,

    @SerializedName("offered_toy")
    val nOfferToyId:Int
)
