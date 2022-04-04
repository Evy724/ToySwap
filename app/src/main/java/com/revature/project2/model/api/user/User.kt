package com.revature.project2.model.api.user

import com.google.gson.annotations.SerializedName

data class User(

    @SerializedName("user_id") val user_id: Int
)
