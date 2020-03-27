package com.jimmyhernandez.reigntest.data.server

import com.jimmyhernandez.domain.HackerNewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TheReignDbService {

    @GET("search_by_date")
    fun getListUsers(@Query("query") query: String): Call<HackerNewsResponse>

}