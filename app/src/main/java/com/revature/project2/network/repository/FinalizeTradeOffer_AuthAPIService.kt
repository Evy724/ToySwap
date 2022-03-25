package com.revature.project2.network.repository

import com.revature.project2.network.FinalizeTradeOffer_Request_API
import com.revature.project2.network.FinalizeTradeOffer_Token_API
import com.revature.project2.network.ViewItem_Request_API
import com.revature.project2.network.ViewItem_Token_API
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface FinalizeTradeOffer_AuthAPIService
{
    @POST("finalizeTradeOffer_auth_token")
    suspend fun getFinalizeTradeOffer_Request_API(
        @Body finalizeTradeOffer_Request_API: FinalizeTradeOffer_Request_API
    ):
            Response<FinalizeTradeOffer_Token_API>
}