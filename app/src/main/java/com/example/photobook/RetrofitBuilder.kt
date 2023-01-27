package com.example.photobook

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

object RetrofitBuilder {
    var api: RetrofitInterface

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/api")
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(RetrofitInterface::class.java)
    }
}