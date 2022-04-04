package com.revature.project2.model.api

import com.revature.project2.model.api.alltoys.AllToyResponse
import com.revature.project2.model.api.alltoys.RequestAllToys
import com.revature.project2.model.api.login.LoginRequest
import com.revature.project2.model.api.login.token
import com.revature.project2.model.api.usertoys.RequestUserToys
import com.revature.project2.model.api.finalizetradeoffer.FinalizeTradeOffer_Request_API
import com.revature.project2.model.api.finalizetradeoffer.FinalizeTradeOffer_Response_API
import com.revature.project2.model.api.finalizetradeoffer.SendTrade_Request_API
import com.revature.project2.model.api.finalizetradeoffer.SendTrade_Response_API
import com.revature.project2.model.api.viewitem.ViewItem_Request_API
import com.revature.project2.model.api.viewitem.ViewItem_Response_API
import retrofit2.Response
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

    @POST("login")
    suspend fun getLoginAuth(@Body LoginRequest:LoginRequest)
            : Response<token>

    @POST("finalizeTradeOffer")
    suspend fun getFinalizeTradeOffer(
        @Body finalizeTradeOffer_Request_API: FinalizeTradeOffer_Request_API
    ):
            Response<FinalizeTradeOffer_Response_API>

    @POST("viewItem")
    suspend fun getViewItem(
        @Body viewItem_Request_API: ViewItem_Request_API
    ):
            Response<ViewItem_Response_API>

    @POST("SendTrade")
    suspend fun getSendTradeOffer_msg(
        @Body sendTradeRequestApi: SendTrade_Request_API
    ):
            Response<SendTrade_Response_API>
}