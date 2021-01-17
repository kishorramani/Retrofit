package com.kishorramani.retrofit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kishorramani.retrofit.model.Post
import com.kishorramani.retrofit.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository) : ViewModel() {

    val myResponse: MutableLiveData<Response<Post>> = MutableLiveData()
    val myResponse2: MutableLiveData<Response<Post>> = MutableLiveData()
    val myCustomPost: MutableLiveData<Response<List<Post>>> = MutableLiveData()
    val myCustomPost2: MutableLiveData<Response<List<Post>>> = MutableLiveData()
    val myCustomPost3: MutableLiveData<Response<List<Post>>> = MutableLiveData()

    fun getPost() {
        viewModelScope.launch {
            val response = repository.getPost()
            myResponse.value = response
        }
    }

    fun getPost2(number: Int) {
        viewModelScope.launch {
            val response = repository.getPost2(number)
            myResponse2.value = response
        }
    }

    fun getCustomPost(userId: Int) {
        viewModelScope.launch {
            val response = repository.getCustomPost(userId)
            myCustomPost.value = response
        }
    }

    fun getCustomPost2(userId: Int, sort: String, order: String) {
        viewModelScope.launch {
            val response = repository.getCustomPost2(userId, sort, order)
            myCustomPost2.value = response
        }
    }

    fun getCustomPost3(userId: Int, options: Map<String, String>) {
        viewModelScope.launch {
            val response = repository.getCustomPost3(userId, options)
            myCustomPost3.value = response
        }
    }

    fun pushPost(post: Post) {
        viewModelScope.launch {
            val response = repository.pushPost(post)
            myResponse.value = response
        }
    }

    fun pushPost2(userId: Int, id: Int, title: String, body: String) {
        viewModelScope.launch {
            val response = repository.pushPost2(userId, id, title, body)
            myResponse.value = response
        }
    }

    fun getPost1() {
        viewModelScope.launch {
            val response = repository.getPost1()
            myResponse.value = response
        }
    }

    fun getPost2() {
        viewModelScope.launch {
            val response = repository.getPost2()
            myResponse.value = response
        }
    }

    fun getPost3(auth: String) {
        viewModelScope.launch {
            val response = repository.getPost3(auth)
            myResponse.value = response
        }
    }

}