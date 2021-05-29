package com.kishorramani.retrofit2.api

import com.kishorramani.retrofit2.model.Todo
import retrofit2.Response
import retrofit2.http.GET

interface TodoAPI {
    //GET - use to get some data from server
    //POST - use to post some data from device to remote server
    //PUT - use to update data on server
    //DELETE - use to delete data

    @GET("/todos")
    suspend fun getTodos(): Response<List<Todo>>

    //if we need to pass the json data then use body
//    @POST("/createTodo")
//    fun createTodo(@Body todo: Todo) : Response<CreateTodoResponse>



}