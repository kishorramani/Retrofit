package com.kishorramani.retrofit.model

import com.google.gson.annotations.SerializedName

data class Post (
//    @SerializedName("userID")     //If you want use some new name compare to response then you have to use @SerializedName
//    val myUserId: Int

    val userId : Int,
    val id : Int,
    val title : String,
    val body : String
)