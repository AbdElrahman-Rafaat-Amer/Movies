package com.abdelrahman.rafaat.movies.network

import com.abdelrahman.rafaat.movies.model.MovieDetails
import com.abdelrahman.rafaat.movies.model.MovieQuery
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RemoteSource {

    suspend fun getCategoryMovies(
        genres: String,
        sort_by: String,
        page: String
    ): Response<MovieQuery>


    suspend fun getTrendingMovie(
        mediaType: String,
        timeWindow: String
    ): Response<MovieQuery>


    suspend fun searchMovie(
        movieName: String
    ): Response<MovieQuery>


    suspend fun getMovieDetails(
        movieId: Long
    ): Response<MovieDetails>

}