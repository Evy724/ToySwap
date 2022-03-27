package com.revature.project2.network.repository

import com.revature.project2.network.FinalizeTradeOffer_Request_API
import com.revature.project2.network.FinalizeTradeOffer_Response_API
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface FinalizeTradeOffer_SuccessAPIService
{
    @POST("finalizeTradeOffer_success_msg")
    suspend fun getFinalizeTradeOffer_Request_API(
        @Body finalizeTradeOffer_Request_API: FinalizeTradeOffer_Request_API
    ):
            Response<FinalizeTradeOffer_Response_API>
}