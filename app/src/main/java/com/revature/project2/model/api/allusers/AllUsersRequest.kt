package com.revature.project2.model.api.allusers

import com.google.gson.annotations.SerializedName

data class AllUsersRequest(

    @SerializedName("sToyTable")
    val sUserList:String,
    @SerializedName("sScope")
    val sScope:String
)
