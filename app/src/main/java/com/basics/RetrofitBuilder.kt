package com.basics

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    private fun getRetrofit(): Retrofit {
        val gson=GsonBuilder()
            .serializeNulls()
            .create()

        val headerInterceptor= Interceptor(object :(Interceptor.Chain) -> Response {
            override fun invoke(chain: Interceptor.Chain): Response {
                return chain.proceed(chain.request().newBuilder().addHeader("headerName","value").build())
            }
        })

        val loggingInterceptor=HttpLoggingInterceptor()
        loggingInterceptor.level=HttpLoggingInterceptor.Level.BODY

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create(gson)) //TODO pass nullable with request
            .addConverterFactory(GsonConverterFactory.create())
//            .client(OkHttpClient.Builder().addInterceptor(loggingInterceptor).addInterceptor(headerInterceptor).build()) // TODO Hare We can use Multiple Interceptor
            .client(OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()) // TODO show http log
            .build()
    }

    val apiService: Api = getRetrofit().create(Api::class.java)
}