package com.revature.project2.network.repository

import com.revature.project2.network.ViewItem_Request_API
import com.revature.project2.network.ViewItem_Token_API
import okhttp3.Response
import retrofit2.http.Body

interface ViewItem_AuthAPIService
{
    suspend fun getLogin(@Body viewItem_Request_API: ViewItem_Request_API): Response<ViewItem_Token_API>
}