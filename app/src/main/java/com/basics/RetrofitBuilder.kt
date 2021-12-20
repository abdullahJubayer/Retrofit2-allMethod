package com.basics

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    private fun getRetrofit(): Retrofit {
        val gson=GsonBuilder()
            .serializeNulls()
            .create()

        val loggingInterceptor=HttpLoggingInterceptor()
        loggingInterceptor.level=HttpLoggingInterceptor.Level.BODY

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create(gson)) //TODO pass nullable with request
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()) // TODO show http log
            .build()
    }

    val apiService: Api = getRetrofit().create(Api::class.java)
}