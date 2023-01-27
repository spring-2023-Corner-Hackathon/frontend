package com.example.photobook

import retrofit2.Call
import retrofit2.http.*
import java.util.*
import kotlin.collections.ArrayList

interface RetrofitInterface {

    @POST("login")
    fun executeLogin(
        @Query("id") id: String,
        @Query("password") password: String
    ): Call<String>

    @POST("register")
    fun executeRegister(
        @Query("id") id: String,
        @Query("password") password: String,
        @Query("nickname") nickname: String
    ): Call<String>


    @POST("photobooks/save")
    fun executeSave(
        @Query("editors") editors:ArrayList<Users>,
        @Query("title") title: String,
        @Query("description") description:String,
        @Query("music") music:ArrayList<Music>,
        @Query("share") share:String,
        @Query("category") category:String,
        @Query("start") start_date: Date,
        @Query("end") end_date:Date
    ): Call<String>
}