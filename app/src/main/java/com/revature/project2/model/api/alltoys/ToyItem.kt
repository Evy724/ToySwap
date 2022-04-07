package com.revature.project2.model.api.alltoys

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Base toy item for the retrieved items from server
 */
@Entity(tableName = "usertoys")
data class ToyItem(

    @SerializedName("toyid")
    @PrimaryKey(autoGenerate = false)
    val id:Int,
    @SerializedName("name")
    @ColumnInfo(name = "name")
    val sName:String,
    @SerializedName("description")
    @ColumnInfo(name = "description")
    val sDescription:String,
    @SerializedName("posterid")
    @ColumnInfo(name = "posterid")
    val posterId:Int,
    @SerializedName("img")
    @ColumnInfo(name = "img")
    val sImagePath:String
)