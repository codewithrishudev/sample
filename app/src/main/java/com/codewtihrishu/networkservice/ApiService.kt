package com.citycarcare.myapplication.networkservice

import com.codewtihrishu.model.HomeResponse

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("search")
    fun homedata(@Query ("term" ) term :String,
        @Query ("media" ) media :String): Call<HomeResponse>

}