package com.revature.project2.model.api.finalizetradeoffer

import com.google.gson.annotations.SerializedName

data class FinalizeTradeOffer_Request_API (

    @SerializedName("item_id")
    val item_id: Int,

    @SerializedName("toy_type")
    val toy_type: String,

    @SerializedName("msg_input")
    val msg_input: String,
        )