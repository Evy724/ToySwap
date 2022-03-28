package com.revature.project2.model.api.viewitem

import com.google.gson.annotations.SerializedName

data class ViewItem_Request_API (

    @SerializedName("viewItem_toy_id")
    val viewItem_toy_id: Int,

    @SerializedName("viewItem_toy_type")
    val viewItem_toy_type: String

    )