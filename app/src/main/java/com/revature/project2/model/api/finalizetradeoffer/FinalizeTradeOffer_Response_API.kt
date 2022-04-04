package com.revature.project2.model.api.finalizetradeoffer

import com.google.gson.annotations.SerializedName

data class FinalizeTradeOffer_Response_API (

    @SerializedName("finalizeTradeOffer_Request_success")
    val finalizeTradeOffer_Request_success: Boolean.Companion = Boolean,

    @SerializedName("finalizeTradeOffer_msg")
    val finalizeTradeOffer_msg: String

)