package com.revature.project2.model.api.viewitem

import com.google.gson.annotations.SerializedName

data class ViewItem_Request_API (

    @SerializedName("posterid")
    val posterid:String,

    @SerializedName("toyid")
    val toyid: Int,

    )