package com.example.movies.data.uitls

import com.example.movies.data.api.Api
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitInstance {

    val api: Api by lazy {
        Retrofit.Builder()
            .baseUrl(Utils_Url.url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
    }


}