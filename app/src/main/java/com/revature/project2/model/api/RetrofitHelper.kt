package com.revature.project2.model.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Dns
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.Inet4Address
import java.net.InetAddress
import java.net.Proxy
import java.util.concurrent.TimeUnit


/**
 * Helper class for loading from a server
 *
 * Contains call functions for our api
 */
object RetrofitHelper {

    private val retrofit: Retrofit

    init {


        /**
         * Create a builder for Retrofit, including the
         *
         * Base URL of our api
         * Converter factory to change between Kotlin code to JSON code
         * CallAdapter to include Coroutine functionality into loading
         */
        val builder = Retrofit.Builder()
            .baseUrl(NetConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())

        /**
         * Logging Interceptor:
         * Adds an interceptor to our okHttp client to allow for logs to be created
         * during server connection
         */
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY


        /**
         * okHttp client using the created interceptor to add logging of timeout to server
         */
        val okHttpClient = OkHttpClient.Builder()
            .dns(DnsSelector())
            .addInterceptor(loggingInterceptor).build()

        // Build our retrofit object using the builder and our okHttp client
        retrofit = builder.client(okHttpClient).build()
      //  retrofit.
    }

    /**
     * Retrieves the Toy service API for use in the API's repository
     */
    fun getAllToysService():ToysApiService{
        return retrofit.create(ToysApiService::class.java)
    }

    /**
     * Retrieves the Profile service API
     */
    fun getProfileService(): ToysApiService {
        return retrofit.create(ToysApiService::class.java)
    }
    fun getHistoryService(): ToysApiService {
        return retrofit.create(ToysApiService::class.java)
    }
    fun getReviewsService(): ToysApiService {
        return retrofit.create(ToysApiService::class.java)
    }
}

class DnsSelector() : Dns {
    override fun lookup(hostname: String): List<InetAddress> {
        return Dns.SYSTEM.lookup(hostname).filter { Inet4Address::class.java.isInstance(it) }
    }
}