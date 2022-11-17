package com.abdelrahman.rafaat.movies.ui.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdelrahman.rafaat.movies.model.Genre
import com.abdelrahman.rafaat.movies.model.MovieResponse
import com.abdelrahman.rafaat.movies.model.RepositoryInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class HomeViewModel(iRepo: RepositoryInterface) : ViewModel() {
    private val _iRepo: RepositoryInterface = iRepo

    private var _movieGenres = MutableLiveData<List<Genre>>()
    var movieGenres: LiveData<List<Genre>> = _movieGenres

    private var _trendingMovies = MutableLiveData<MovieResponse>()
    var trendingMovies: LiveData<MovieResponse> = _trendingMovies

    private var _upComingMovies = MutableLiveData<MovieResponse>()
    var upComingMovies: LiveData<MovieResponse> = _upComingMovies

    private var _topRatedMovies = MutableLiveData<MovieResponse>()
    var topRatedMovies: LiveData<MovieResponse> = _topRatedMovies

    private var _popularMovies = MutableLiveData<MovieResponse>()
    var popularMovies: LiveData<MovieResponse> = _popularMovies

    fun getMovieGenres() {
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

    fun getTrendingMovie(mediaType: String, timeWindow: String) {
        viewModelScope.launch {
            val response = _iRepo.getTrendingMovie(mediaType, timeWindow)
            withContext(Dispatchers.Main) {
                if (response.code() == 200) {
                    _trendingMovies.postValue(response.body())
                } else {
                    _trendingMovies.postValue(MovieResponse(0, emptyList(), 0, 0))
                }
            }
        }
    }


    fun getUpcomingMovies() {
        viewModelScope.launch {
            val response = _iRepo.getUpcomingMovies()
            withContext(Dispatchers.Main) {
                if (response.code() == 200) {
                    _upComingMovies.postValue(response.body())
                } else {
                    _upComingMovies.postValue(MovieResponse(0, emptyList(), 0, 0))
                }
            }
        }
    }

    fun getTopRatedMovies() {
        viewModelScope.launch {
            val response = _iRepo.getTopRatedMovies()
            withContext(Dispatchers.Main) {
                if (response.code() == 200) {
                    _topRatedMovies.postValue(response.body())
                } else {
                    _topRatedMovies.postValue(MovieResponse(0, emptyList(), 0, 0))
                }
            }
        }
    }

    fun getPopularMovies() {
        viewModelScope.launch {
            val response = _iRepo.getPopularMovies()
            withContext(Dispatchers.Main) {
                if (response.code() == 200) {
                    _popularMovies.postValue(response.body())
                } else {
                    _popularMovies.postValue(MovieResponse(0, emptyList(), 0, 0))
                }
            }
        }
    }

}