package com.kishorramani.retrofit.api

import com.kishorramani.retrofit.model.Post
import retrofit2.Response
import retrofit2.http.*

interface SimpleApi {

    //Step1: Get request - https://jsonplaceholder.typicode.com/posts/1
    @GET("posts/1")
    suspend fun getPost(): Response<Post>

    //Step2: Above end point is hard coded, customize end point and get value from user
    //link - https://jsonplaceholder.typicode.com/posts/15
    @GET("posts/{postNumber}")
    suspend fun getPost2(
        @Path("postNumber") number: Int
    ): Response<Post>

    //step2b: Add
    //link - https://jsonplaceholder.typicode.com/posts?userId=7
    //to add userId - in postman we have to use Params
    @GET("posts")
    suspend fun getCustomPost(
        @Query("userId") userId: Int
    ): Response<List<Post>>

    //step2c: add more parameter - Order post in desc order
    //link: - https://jsonplaceholder.typicode.com/posts?userId=7&_sort=id&_order=desc
    @GET("posts")
    suspend fun getCustomPost2(
        @Query("userId") userId: Int,
        @Query("_sort") sort: String,
        @Query("_order") order: String
    ): Response<List<Post>>

    //step2d: Reduce query parameter, You can add multiple query using hashmap
    //link: - same as above
    //https://jsonplaceholder.typicode.com/posts?userId=7&_sort=id&_order=desc
    @GET("posts")
    suspend fun getCustomPost3(
        @Query("userId") userId: Int,
        @QueryMap option: Map<String, String>
    ): Response<List<Post>>

    //step3a: Post request - It's used to send data to server to create/update resource
    //send data is stored in request body not in url
    //Use FormUrlEncoded
    //Request made with this annotation will have application/x-www-form-urlencoded 'MIME type instead of JSON like the regular POST request
    @POST("posts")
    suspend fun pushPost(
        @Body post: Post
    ): Response<Post>

    //step3b: FormUrlEncoded - if data is encoded by FormUrlEncoded then retrofit send request to server using key value separated by &
    //like this - userId=1 & id=1 & title=title & body=description
    @FormUrlEncoded
    @POST("posts")
    suspend fun pushPost2(
        @Field("userId") userId: Int,
        @Field("id") id: Int,
        @Field("title") title: String,
        @Field("body") body: String
    ): Response<Post>

    //Step4a: Custom Request Headers - Mostly used to add metadata information in an API request and response
    //Example - Authentication tokens, session id, content type, app version, content length
    //Content-Type: application/json
    //Host: jsonplaceholder.typcon.com
    //Connection: Keep-Alive
    //User-Agent: okhttp/4.5.0
    //Authorization: TOKEN_KEY
//    @Header()
    @GET("posts/1")
    suspend fun getPost1(): Response<Post>

    //step4b - method 2 - new method for add header
    @Headers(
        "Authorization: 123123123",
        "Platform: Android"
    )
    @GET("posts/1")
    suspend fun getPost2(): Response<Post>

    //step4c - method 3 - Add header dynamically
    @GET("posts/1")
    suspend fun getPost3(@Header("Auth") auth: String): Response<Post>

}