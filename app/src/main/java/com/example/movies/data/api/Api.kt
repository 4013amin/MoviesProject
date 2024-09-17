package com.example.movies.data.api

import com.example.movies.data.models.MoviesList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    //https://moviesapi.ir/api/v1/movies?page={page}
    @GET("movies?")
    suspend fun getMovies(
        @Query("page") page: Int
    ): Response<MoviesList>

}