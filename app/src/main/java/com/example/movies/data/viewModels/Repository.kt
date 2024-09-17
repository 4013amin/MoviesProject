package com.example.movies.data.viewModels
import com.example.movies.data.models.MoviesList
import com.example.movies.data.uitls.RetrofitInstance
import retrofit2.Response

class Repository {

    suspend fun getMoveList(page: Int): Response<MoviesList> {
        return RetrofitInstance.api.getMovies(page)
    }

}