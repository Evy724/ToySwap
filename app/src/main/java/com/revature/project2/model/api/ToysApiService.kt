package com.revature.project2.model.api

import com.revature.project2.model.api.alltoys.AllToyResponse
import com.revature.project2.model.api.alltoys.RequestAllToys
import com.revature.project2.model.api.usertoys.RequestUserToys
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Service interface containing all service calls for the Api
 */
interface ToysApiService {

    /**
     * Retrieves All toys from the server
     */
    @POST("alltoys")
    suspend fun getToys(@Body RequestAllToys: RequestAllToys)
            : AllToyResponse

    //Add other calls here

    @POST("usertoys")
    suspend fun getUserToys(@Body RequestUserToys:RequestUserToys)
            : AllToyResponse


}