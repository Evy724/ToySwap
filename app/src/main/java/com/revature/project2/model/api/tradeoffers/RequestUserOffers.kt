package com.revature.project2.model.api.tradeoffers

import com.google.gson.annotations.SerializedName

data class RequestUserOffers(

    @SerializedName("sTable")
    val sTable:String,
    @SerializedName("user_id")
    val nUserId:Int)
