package com.revature.project2.model.api.tradeoffers

import com.google.gson.annotations.SerializedName
import com.revature.project2.model.api.alltoys.ToyItem

data class ResponseUserOffers(

    @SerializedName("offers")
    var results:ArrayList<TradeOffer>)
