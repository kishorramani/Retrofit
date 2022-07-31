package com.kishorramani.retrofit.api

import com.kishorramani.retrofit.model.Post
import retrofit2.Response
import retrofit2.http.*

interface SimpleApi {

    //Step1: Get request - https://jsonplaceholder.typicode.com/posts/1
    //{
    //  "userId": 1,
    //  "id": 1,
    //  "title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
    //  "body": "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"
    //}
    @GET("posts/1")
    suspend fun getPost(): Response<Post>

    //===================================================

    //Step2: Above end point is hard coded, customize end point and get value from user
    //link - https://jsonplaceholder.typicode.com/posts/15
    //{
    //  "userId": 2,
    //  "id": 15,
    //  "title": "eveniet quod temporibus",
    //  "body": "reprehenderit quos placeat\nvelit minima officia dolores impedit repudiandae molestiae nam\nvoluptas recusandae quis delectus\nofficiis harum fugiat vitae"
    //}
    @GET("posts/{postNumber}")
    suspend fun getPost2(
        @Path("postNumber") number: Int
    ): Response<Post>

    //===================================================

    //step2b: Add
    //link - https://jsonplaceholder.typicode.com/posts?userId=7
    //to add userId - in postman we have to use Params
    //[
    //  {
    //    "userId": 7,
    //    "id": 61,
    //    "title": "voluptatem doloribus consectetur est ut ducimus",
    //    "body": "ab nemo optio odio\ndelectus tenetur corporis similique nobis repellendus rerum omnis facilis\nvero blanditiis debitis in nesciunt doloribus dicta dolores\nmagnam minus velit"
    //  },
    //  {
    //    "userId": 7,
    //    "id": 62,
    //    "title": "beatae enim quia vel",
    //    "body": "enim aspernatur illo distinctio quae praesentium\nbeatae alias amet delectus qui voluptate distinctio\nodit sint accusantium autem omnis\nquo molestiae omnis ea eveniet optio"
    //  }
    //]
    @GET("posts")
    suspend fun getCustomPost(
        @Query("userId") userId: Int
    ): Response<List<Post>>

    //===================================================

    //step2c: add more parameter - Order post in desc order
    //link: - https://jsonplaceholder.typicode.com/posts?userId=7&_sort=id&_order=desc
    //[
    //  {
    //    "userId": 7,
    //    "id": 70,
    //    "title": "voluptatem laborum magni",
    //    "body": "sunt repellendus quae\nest asperiores aut deleniti esse accusamus repellendus quia aut\nquia dolorem unde\neum tempora esse dolore"
    //  },
    //  {
    //    "userId": 7,
    //    "id": 69,
    //    "title": "fugiat quod pariatur odit minima",
    //    "body": "officiis error culpa consequatur modi asperiores et\ndolorum assumenda voluptas et vel qui aut vel rerum\nvoluptatum quisquam perspiciatis quia rerum consequatur totam quas\nsequi commodi repudiandae asperiores et saepe a"
    //  }
    //]
    @GET("posts")
    suspend fun getCustomPost2(
        @Query("userId") userId: Int,
        @Query("_sort") sort: String,
        @Query("_order") order: String
    ): Response<List<Post>>

    //=========================POST REQUEST==========================

    //step2d: Reduce query parameter, You can add multiple query using hashmap
    //link: - same as above
    //https://jsonplaceholder.typicode.com/posts?userId=7&_sort=id&_order=desc
    @GET("posts")
    suspend fun getCustomPost3(
        @Query("userId") userId: Int,
        @QueryMap option: Map<String, String>
    ): Response<List<Post>>

    //===================================================

    //step3a: Post request - It's used to send data to server to create/update resource
    //send data is stored in request body not in url
    //Use FormUrlEncoded
    //Request made with this annotation will have application/x-www-form-urlencoded 'MIME type instead of JSON like the regular POST request
    @POST("posts")
    suspend fun pushPost(
        @Body post: Post
    ): Response<Post>

    //===================================================

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

    //===================================================

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