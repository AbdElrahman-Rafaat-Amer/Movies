package com.abdelrahman.rafaat.movies.model

import retrofit2.Response

interface RepositoryInterface {
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