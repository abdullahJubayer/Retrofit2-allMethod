package com.basics

import retrofit2.Call
import retrofit2.http.*

interface Api {

    @GET("posts")
    fun getPosts():Call<List<Post>>

    @GET("posts")
    fun getPosts(@Query("userId") userId: Int):Call<List<Post>>

    @GET("posts")
    fun getPosts(@Query("userId") userId: Int,@Query("_sort") sort:String,@Query("_order") order:String):Call<List<Post>>

    @GET("posts")
    fun getPosts(@Query("userId") userId: Array<Int>,@Query("_sort") sort:String,@Query("_order") order:String):Call<List<Post>>

    @GET("posts")
    fun getPosts(@QueryMap map: Map<String,String>):Call<List<Post>>

    @GET
    fun getPosts(@Url url:String):Call<List<Post>>


    @GET("/posts/{id}/comments")
    fun getComments(@Path("id") postId:Int ):Call<List<Comment>>

    @GET
    fun getComments(@Url url:String ):Call<List<Comment>>
}