package com.revature.project2.model.api.user

import com.google.gson.annotations.SerializedName

data class Review(

    @SerializedName("review_id") val review_id: Int,
    @SerializedName("from") val user: User,
    @SerializedName("message") val description: String,
    @SerializedName("rating") var rating: Int
)
