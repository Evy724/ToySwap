package com.revature.project2.model.api.allusers

import com.google.gson.annotations.SerializedName
import com.revature.project2.model.api.alltoys.ToyItem

data class AllUsersResponse (

    @SerializedName("users")
    var results:ArrayList<User>

)