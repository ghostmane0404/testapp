package com.example.testproject.core.data.network

import com.example.testproject.core.data.network.responses.TopGamesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("games/top")
    suspend fun getTop(@Query("limit")limit:Int,@Query("offset")offset:Int): Response<TopGamesResponse>

}