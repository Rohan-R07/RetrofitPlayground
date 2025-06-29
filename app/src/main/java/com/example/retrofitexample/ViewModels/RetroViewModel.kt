package com.example.retrofitexample.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.retrofitexample.DataModel.PostsItem
import com.example.retrofitexample.Retrofit.GetInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class RetroViewModel : ViewModel() {

    private val _posts = MutableStateFlow<List<PostsItem>>(emptyList())
    val posts = _posts


    val isLoading = MutableStateFlow(false)

    val couldntLoad = MutableStateFlow(false)

    init {
        fetchPosts()
    }

     fun fetchPosts() {
        viewModelScope.launch {
        isLoading.value = true
//                isLoading.value = true
            try {
                val data = GetInstance.api.getPosts()
                _posts.value = data
                isLoading.value = false
            } catch (e: Exception) {
                e.printStackTrace()
                isLoading.value = true
                couldntLoad.value = true
            }
        }


    }

}