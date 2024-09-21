package com.example.movies.data.api

import android.telecom.Call.Details
import com.example.movies.data.models.MoviesList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    //https://moviesapi.ir/api/v1/movies?page={page}
    @GET("movies?")
    suspend fun getMovies(
        @Query("page") page: Int
    ): Response<MoviesList>

    @GET("movies/{movies_id}")
    suspend fun getDetailsById(
        @Path("movies_id") id: Int
    ): Response<com.example.movies.data.models.Details>

}