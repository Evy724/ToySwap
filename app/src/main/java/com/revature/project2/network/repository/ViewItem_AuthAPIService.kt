package com.revature.project2.network.repository

import com.revature.project2.network.ViewItem_Request_API
import com.revature.project2.network.ViewItem_Token_API
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ViewItem_AuthAPIService
{
    @POST("viewItem_auth_token")
    suspend fun getViewItem_Request_API(
        @Body viewItem_Request_API: ViewItem_Request_API
    ):
            Response<ViewItem_Token_API>
}