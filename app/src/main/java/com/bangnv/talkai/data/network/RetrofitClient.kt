package com.bangnv.talkai.data.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://api.openai.com/v1/"

    // Initialize Moshi with Kotlin adapter
    private val moshi: Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory()) // Add Kotlin support for Moshi
        .build()

    // Create Retrofit client using Moshi converter and OkHttp client
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi)) // Use MoshiConverterFactory
        .client(OkHttpClientProvider.client)
        .build()

    val instance: AppApi = retrofit.create(AppApi::class.java)
}
