package com.example.photobook

import retrofit2.Call
import retrofit2.http.*

interface RetrofitInterface {

    @POST("login")
    fun executeLogin(
        @Query("id") id: String,
        @Query("password") password: String
    ): Call<LoginResult>

    @POST("register")
    fun executeRegister(
        @Query("id") id: String,
        @Query("password") password: String,
        @Query("nickname") nickname: String
    ): Call<String>

}