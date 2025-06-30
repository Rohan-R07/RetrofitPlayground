package com.example.retrofitexample.Retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


// CREATING RETROFIT INSTANCE

object GetInstance {

//    https://jsonplaceholder.typicode.com/posts

    val api by lazy {
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

    }


}