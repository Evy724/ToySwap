package com.revature.project2.model.api.allusers

import com.google.gson.annotations.SerializedName

data class User(

    @SerializedName("username")
    val sName: String,
    @SerializedName("password")
    val sPass:String,
    @SerializedName("userid")
    val nUserId:Int
)