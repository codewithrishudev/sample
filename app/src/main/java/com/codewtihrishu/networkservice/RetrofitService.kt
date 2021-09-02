package com.citycarcare.myapplication.networkservice

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitService {
    companion object {
        private const val BASE_URL = "https://itunes.apple.com/"
    }

    private fun getClient(): OkHttpClient {

        return OkHttpClient.Builder()
            .readTimeout(2, TimeUnit.MINUTES).writeTimeout(2, TimeUnit.MINUTES).build()
    }





    fun getServiceApi(retrofit: Retrofit) = retrofit.create(ApiService::class.java)

    fun getRetrofit(baseurl: String) = Retrofit.Builder()
        .baseUrl(baseurl)
        .client(getClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    val videolist: ApiService = getServiceApi(getRetrofit(BASE_URL))


}