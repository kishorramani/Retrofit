package com.kishorramani.retrofit.api

import com.kishorramani.retrofit.utils.Constant.Companion.AUTH_CONTENT
import com.kishorramani.retrofit.utils.Constant.Companion.BASE_URL
import com.kishorramani.retrofit.utils.Constant.Companion.CONTENT_TYPE
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val client = OkHttpClient.Builder().apply {
        addInterceptor(MyInterceptor())
    }.build()

    private val retrofit by lazy {
        /*val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor { chain ->
            val request = chain.request()
                .newBuilder()
                .addHeader(CONTENT_TYPE, AUTH_CONTENT)
                .build()
            chain.proceed(request)
        }

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BASIC
        logging.level = HttpLoggingInterceptor.Level.BODY*/

//      .client(httpClient.addInterceptor(logging).build())

        /*Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()*/

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)     //added client for adding headers
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: SimpleApi by lazy {
        retrofit.create(SimpleApi::class.java)
    }
}