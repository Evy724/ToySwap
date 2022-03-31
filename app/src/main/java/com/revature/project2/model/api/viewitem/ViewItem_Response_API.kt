package com.revature.project2.model.api.viewitem

import com.google.gson.annotations.SerializedName
import com.revature.project2.model.api.alltoys.ToyItem

data class ViewItem_Response_API
    (
    @SerializedName("viewItemRequestTrade_success_msg")
    val viewItemRequestTrade_success_msg: String,

    @SerializedName("viewItemToy")
    var result: ToyItem
    )