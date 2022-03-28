package com.revature.project2.network.repository

import com.revature.project2.network.ViewItem_Request_API
import com.revature.project2.network.ViewItem_Response_API
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ViewItem_SuccessAPIService
{
    @POST("viewItemRequestTrade_success_msg")
    suspend fun getViewItem_Request_API(
        @Body viewItem_Request_API: ViewItem_Request_API
    ):
            Response<ViewItem_Response_API>
}