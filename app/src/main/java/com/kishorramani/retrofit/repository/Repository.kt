package com.kishorramani.retrofit.repository

import android.icu.text.UnicodeSet
import android.media.MediaRouter
import com.kishorramani.retrofit.api.RetrofitInstance
import com.kishorramani.retrofit.model.Post
import retrofit2.Response
import retrofit2.http.Header

class Repository {

    suspend fun getPost(): Response<Post> {
        return RetrofitInstance.api.getPost()
    }

    suspend fun getPost2(number: Int): Response<Post> {
        return RetrofitInstance.api.getPost2(number)
    }

    suspend fun getCustomPost(userId: Int): Response<List<Post>> {
        return RetrofitInstance.api.getCustomPost(userId)
    }

    suspend fun getCustomPost2(userId: Int, sort: String, order: String): Response<List<Post>> {
        return RetrofitInstance.api.getCustomPost2(userId, sort, order)
    }

    suspend fun getCustomPost3(userId: Int, options: Map<String, String>): Response<List<Post>> {
        return RetrofitInstance.api.getCustomPost3(userId, options)
    }

    suspend fun pushPost(post: Post): Response<Post> {
        return RetrofitInstance.api.pushPost(post)
    }

    suspend fun pushPost2(userId: Int, id: Int, title: String, body: String): Response<Post> {
        return RetrofitInstance.api.pushPost2(userId, id, title, body)
    }

    suspend fun getPost1(): Response<Post> {
        return RetrofitInstance.api.getPost1()
    }

    suspend fun getPost2(): Response<Post> {
        return RetrofitInstance.api.getPost2()
    }

    suspend fun getPost3(auth: String): Response<Post> {
        return RetrofitInstance.api.getPost3(auth)
    }


}