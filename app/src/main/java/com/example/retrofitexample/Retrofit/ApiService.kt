package com.example.retrofitexample.Retrofit

import com.example.retrofitexample.DataModel.PostsItem
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("posts")
    suspend fun getPosts() : List<PostsItem>


    @POST("posts")
    suspend fun ceatePost(@Body updatedData: PostsItem) : PostsItem


    @DELETE("/posts/1")
    suspend fun deletePost(@Body deleteableData : PostsItem) : PostsItem

}