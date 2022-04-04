package com.revature.project2.model.api.allusers

import com.google.gson.annotations.SerializedName

data class User(

    @SerializedName("username")
    val sUserName: String,
    @SerializedName("password")
    val sPass:String,
    @SerializedName("userid")
    val nUserId:Int,
    @SerializedName("name")
    val sName:String,
    @SerializedName("email")
    val sEmail:String
)