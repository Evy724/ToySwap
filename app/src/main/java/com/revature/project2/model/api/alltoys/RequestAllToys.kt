package com.revature.project2.model.api.alltoys

import com.google.gson.annotations.SerializedName

/**
 * The request we send to the server
 */
data class RequestAllToys(

    @SerializedName("sToyTable")
    val sToyTable:String,
    @SerializedName("sScope")
    val sScope:String
)
