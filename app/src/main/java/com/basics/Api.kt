package com.basics

import retrofit2.Call
import retrofit2.http.*

interface Api {

    //GET METHOD
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

    //GET METHOD

    //POST METHOD
    @POST("posts")
    fun createPost(@Body post: Post):Call<Post>

    //TODO NOT WORK
    @POST("posts")
    fun createPost(@Body post: List<Post>):Call<List<Post>>

    @FormUrlEncoded
    @POST("posts")
    fun createPost(@Field("userId") userId: Int,@Field("title") title:String,@Field("body")body:String):Call<Post>

    @FormUrlEncoded
    @POST("posts")
    fun createPost(@FieldMap post:Map<String,String>):Call<Post>
    //POST METHOD

    @PUT("posts/{id}")
    fun updatePost(@Path("id") id:Int, @Body post: Post):Call<Post>

    @FormUrlEncoded
    @PATCH("posts/{id}")
    fun updatePostField(@Path("id") id:Int,@Field("title") title: String):Call<Post>

    @DELETE("posts/{id}")
    fun deletePost(@Path("id") id:Int):Call<Void>

    @Headers("Static Header : 1234")
    @PUT("posts/{id}")
    fun updatePostWithHeaders(@Path("id") id:Int, @Body post: Post):Call<Post>

    //TODO Not Working
//    @Headers({"Static-Header: 1234", "Static-Header2: 456"})
//    @PUT("posts/{id}")
//    fun updatePostWithHeadersArray(@Path("id") id:Int, @Body post: Post):Call<Post>

    @PUT("posts/{id}")
    fun updatePostWithHeader(@Header("dynamic-header") heder:String, @Path("id") id:Int, @Body post: Post):Call<Post>

    @PUT("posts/{id}")
    fun updatePostWithHeaderMap(@HeaderMap headers:Map<String,String>, @Path("id") id:Int, @Body post: Post):Call<Post>
}
