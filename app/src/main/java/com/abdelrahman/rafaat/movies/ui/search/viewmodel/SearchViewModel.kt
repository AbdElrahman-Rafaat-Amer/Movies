package com.abdelrahman.rafaat.movies.ui.search.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdelrahman.rafaat.movies.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class SearchViewModel(iRepo: RepositoryInterface) : ViewModel() {
    private val _iRepo: RepositoryInterface = iRepo

    private var _movies = MutableLiveData<MovieResponse>()
    var movies: LiveData<MovieResponse> = _movies

    private var _movieGenres = MutableLiveData<List<Genre>>()
    var movieGenres: LiveData<List<Genre>> = _movieGenres

    private var _regions = MutableLiveData<List<Region>>()
    var regions: LiveData<List<Region>> = _regions

    init {
        getMovieGenres()
        getRegions()
    }

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

    fun discoverMovie(genre: String, sortBy: String, region: String, year: String, page: Int) {
        viewModelScope.launch {
            val response = _iRepo.discoverMovie(genre, sortBy, region, year, page)
            withContext(Dispatchers.Main) {
                if (response.code() == 200) {
                    _movies.postValue(response.body())
                } else {
                    _movies.postValue(MovieResponse(0, emptyList(), 0, 0))
                }
            }
        }
    }

    private fun getMovieGenres() {
        viewModelScope.launch {
            val response = _iRepo.getMovieGenres()
            withContext(Dispatchers.Main) {
                if (response.code() == 200) {
                    _movieGenres.postValue(response.body()?.genres)
                } else {
                    _movieGenres.postValue(emptyList())
                }
            }
        }
    }

    private fun getRegions() {
        viewModelScope.launch {
            val response = _iRepo.getRegions()
            withContext(Dispatchers.Main) {
                if (response.code() == 200) {
                    _regions.postValue(response.body())
                } else {
                    _regions.postValue(emptyList())
                }
            }
        }
    }
}