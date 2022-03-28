package com.revature.project2.model.api.login

import com.google.gson.annotations.SerializedName

data class token(
    @SerializedName("auth_token")
    val accessToke:String
)
