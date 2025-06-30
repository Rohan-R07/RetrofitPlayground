package com.example.retrofitexample.Retrofit

import com.example.retrofitexample.DataModel.PostsItem
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @GET("posts")
    suspend fun getPosts() : List<PostsItem>


    @POST("posts")
    suspend fun ceatePost(@Body updatedData: PostsItem) : PostsItem


    @DELETE("posts/{id}")
    suspend fun deletePosts(@Path("id") id : Int) : Response<Void>

}