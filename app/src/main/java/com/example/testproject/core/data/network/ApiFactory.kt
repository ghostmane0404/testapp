package com.example.testproject.core.data.network

import android.content.Context
import com.example.testproject.core.data.storage.PreferencesUtil
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import com.example.testproject.BuildConfig

class ApiFactory(var context: Context) {

    private lateinit var retrofit: Retrofit


    private fun getRetrofitClient(): Retrofit? {
        var logging: HttpLoggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val oktHttpClient = OkHttpClient.Builder()
            .connectTimeout(
                5, TimeUnit.SECONDS
            )
            .writeTimeout(5, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS)
        oktHttpClient.addInterceptor(logging)

        retrofit = Retrofit.Builder()
            .baseUrl("${BuildConfig.Base_URL}kraken/")
            .client(oktHttpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

        return retrofit
    }

    private fun getLoginRetrofitClient(): Retrofit? {
        var logging: HttpLoggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)


        val oktHttpClient = OkHttpClient.Builder()
            .connectTimeout(
                5, TimeUnit.SECONDS
            )
            .writeTimeout(5, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS)

        oktHttpClient.addInterceptor(HeaderInterceptor()).addInterceptor(logging)
        retrofit = Retrofit.Builder()
            .baseUrl("${BuildConfig.Base_URL}kraken/")
            .client(oktHttpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

        return retrofit
    }

    val apiInterface: ApiInterface = getRetrofitClient()!!.create(ApiInterface::class.java)
    val loginApiInterface: ApiInterface =
        getLoginRetrofitClient()!!.create(ApiInterface::class.java)

    class HeaderInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            var request = chain.request()
            request = request.newBuilder()
                .addHeader("Client-ID", "sd4grh0omdj9a31exnpikhrmsu3v46")
                .addHeader("Accept","application/vnd.twitchtv.v5+json")
                .build()
            return chain.proceed(request)
        }
    }
}