package com.abdelrahman.rafaat.movies.ui.search.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdelrahman.rafaat.movies.model.MovieResponse
import com.abdelrahman.rafaat.movies.model.RepositoryInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class SearchViewModel(iRepo: RepositoryInterface) : ViewModel() {
    private val _iRepo: RepositoryInterface = iRepo

    private var _movies = MutableLiveData<MovieResponse>()
    var movies: LiveData<MovieResponse> = _movies


    fun searchMovie(movieName: String, page: Int) {
        viewModelScope.launch {
            val response = _iRepo.searchMovie(movieName, page)
            withContext(Dispatchers.Main) {
                if (response.code() == 200) {
                    _movies.postValue(response.body())
                } else {
                    _movies.postValue(MovieResponse(0, emptyList(), 0, 0))
                }
            }
        }
    }
}