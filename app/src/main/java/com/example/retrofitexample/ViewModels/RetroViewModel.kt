package com.example.retrofitexample.ViewModels

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.retrofitexample.DataModel.PostsItem
import com.example.retrofitexample.Retrofit.GetInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import okhttp3.Response

class RetroViewModel : ViewModel() {

    private val _posts = MutableStateFlow<List<PostsItem>>(emptyList())
    val posts = _posts

    private val _deletePost = MutableStateFlow<PostsItem?>(null)
    val deleePost = _deletePost

    val isLoading = MutableStateFlow(false)

    val couldntLoad = MutableStateFlow(false)

    private val _updatedPost = MutableStateFlow<PostsItem?>(null)
    val updatedPost = _updatedPost


    init {
        fetchPosts()
    }

    fun fetchPosts() {
        viewModelScope.launch {
            isLoading.value = true
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


    fun createPosts(updatedData: PostsItem,contex: Context) {
        viewModelScope.launch {
            try {
                val result = GetInstance.api.ceatePost(updatedData)
                _updatedPost.value = result
                Toast.makeText(contex,"Yes working", Toast.LENGTH_SHORT).show()

            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(contex,"Not working ", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun deletePost(id: Int,contex: Context){
        viewModelScope.launch {
            try {
                val delete = GetInstance.api.deletePosts(id)
                if (delete.isSuccessful)
                {
                    Toast.makeText(contex,"Working Bro", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(contex,"Not Working Bro ${delete.code()}", Toast.LENGTH_SHORT).show()

                }

            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }


//    fun deletablePosts(deletedItem: PostsItem){
//        viewModelScope.launch {
//            val deletedItem = GetInstance.api.deletePost(deletedItem)
//            deletePost.value = deletedItem
//        }
//    }

}