package com.revature.project2.model.api.alltoys

import com.google.gson.annotations.SerializedName

/**
 * Response object for retrieving data from the server
 */
data class AllToyResponse (

    @SerializedName("toys")
    var results:ArrayList<ToyItem>

)