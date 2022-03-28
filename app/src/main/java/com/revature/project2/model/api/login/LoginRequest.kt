package com.revature.project2.model.api.login

import com.google.gson.annotations.SerializedName

data class LoginRequest(

    @SerializedName("username")
    val sUsername:String,

    @SerializedName("password")
    val sPassword:String
)
