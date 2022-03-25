package com.revature.project2.network.repository

import com.revature.project2.network.FinalizeTradeOffer_Request_API
import com.revature.project2.network.FinalizeTradeOffer_Token_API
import com.revature.project2.network.ViewItem_Request_API
import com.revature.project2.network.ViewItem_Token_API
import okhttp3.Response
import retrofit2.http.Body

interface FinalizeTradeOffer_AuthAPIService
{
    suspend fun getLogin(@Body finalizeTradeOffer_Request_API: FinalizeTradeOffer_Request_API): Response<FinalizeTradeOffer_Token_API>
}