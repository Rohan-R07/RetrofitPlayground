package com.example.retrofitexample.DataModel

data class PostsItem(
    val body: String,
    val id: Int = 0,
    val title: String,
    val userId: Int
)