package com.example.movies.data.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.data.models.Data
import com.example.movies.data.models.Details
import com.example.movies.paging.paginationFactory
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {

    private val repository = Repository()
    var state by mutableStateOf(ScreenState())
    private val paginationFactory = paginationFactory(
        initPage = state.page,
        onloadUpdated = {
            state = state.copy(
                isLoading = it
            )
        },
        onRequest = {
            repository.getMoveList(it)
        },
        getNextKey = {
            state.page + 1
        },
        onError = {
            state = state.copy(
                error = it?.localizedMessage,
                isLoading = false
            )
        },
        onsucssuc = { item, newPage ->
            state = state.copy(
                movies = state.movies + item.data,
                page = newPage,
                endReached = state.page == 25,
            )
        }
    )

    init {
        loadNextItem()
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

    fun getDetailsById(id: Int) {
        viewModelScope.launch {
            val response = repository.getDetailsById(id)
            if (response.isSuccessful) {
                state = state.copy(
                    detailsScreen = response.body()!!
                )
            } else {
                state = state.copy(error = "Failed to load data", isLoading = false)
            }
        }
    }


    fun loadNextItem() {
        viewModelScope.launch {
            paginationFactory.loadNexPage()
        }
    }


}

data class ScreenState(
    val movies: List<Data> = emptyList(),
    val page: Int = 1,
    val isLoading: Boolean = false,
    val endReached: Boolean = false,
    val error: String? = null,
    val detailsScreen: Details = Details(),
)
