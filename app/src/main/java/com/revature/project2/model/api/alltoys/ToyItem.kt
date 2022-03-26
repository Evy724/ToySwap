package com.revature.project2.model.api.alltoys

import com.google.gson.annotations.SerializedName

/**
 * Base toy item for the retrieved items from server
 */
data class ToyItem(

    @SerializedName("toyid")
    val id:Int,
    @SerializedName("name")
    val sName:String,
    @SerializedName("description")
    val sDescription:String,
    @SerializedName("posterId")
    val posterId:Int,
    @SerializedName("img")
    val sImagePath:String
)