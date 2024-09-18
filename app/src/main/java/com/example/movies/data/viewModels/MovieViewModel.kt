package com.example.movies.data.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.data.models.Data
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {

    private val repository = Repository()
    var state by mutableStateOf(ScreenState())

    init {
        loadNextItems() // Load initial data
    }

    fun loadNextItems() {
        if (state.isLoading || state.endReached) return

        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val response = repository.getMoveList(state.page)
            if (response.isSuccessful) {
                val newMovies = response.body()?.data.orEmpty()
                state = state.copy(
                    movies = state.movies + newMovies,
                    page = state.page + 1,
                    endReached = newMovies.isEmpty(),
                    isLoading = false
                )
            } else {
                state = state.copy(error = "Failed to load data", isLoading = false)
            }
        }
    }
}

data class ScreenState(
    val movies: List<Data> = emptyList(),
    val page: Int = 1,
    val isLoading: Boolean = false,
    val endReached: Boolean = false,
    val error: String? = null
)
