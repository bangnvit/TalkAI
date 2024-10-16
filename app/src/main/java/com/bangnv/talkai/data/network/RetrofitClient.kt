package com.bangnv.talkai.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object  RetrofitClient {
    private const val BASE_URL = "https://api.openai.com/v1/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClientProvider.client)
        .build()

    val instance: AppApi = retrofit.create(AppApi::class.java)
}