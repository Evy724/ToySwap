package com.revature.project2.model.api.usertoys

import com.google.gson.annotations.SerializedName

data class RequestUserToys (

    @SerializedName("posterid")
    val sKey:String,
    @SerializedName("id")
    val sValue:String
        )