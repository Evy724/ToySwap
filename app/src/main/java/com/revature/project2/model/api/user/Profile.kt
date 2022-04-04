package com.revature.project2.model.api.user

import android.media.Image
import com.google.gson.annotations.SerializedName

data class Profile(

    @SerializedName("profile_picture") val profile_picture: Image,
    @SerializedName("first_name") val firstName: String,
    @SerializedName("last_name") val lastName: String,
    @SerializedName("email") val email: String
)

