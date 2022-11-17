package com.abdelrahman.rafaat.movies.model

import retrofit2.Response

interface RepositoryInterface {
    suspend fun getCategoryMovies(
        genres: String,
        sortBy: String,
        page: String
    ): Response<MovieResponse>

    suspend fun searchMovie(
        movieName: String
    ): Response<MovieResponse>


    suspend fun getMovieGenres(): Response<GenreResponse>

    suspend fun getTrendingMovie(
        mediaType: String,
        timeWindow: String
    ): Response<MovieResponse>


    suspend fun getUpcomingMovies(): Response<MovieResponse>

    suspend fun getTopRatedMovies(): Response<MovieResponse>

    suspend fun getPopularMovies(): Response<MovieResponse>

    suspend fun getMovieDetails(movieId: Long): Response<MovieDetails>

}