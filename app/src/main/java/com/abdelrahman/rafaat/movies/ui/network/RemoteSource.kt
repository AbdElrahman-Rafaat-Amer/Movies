package com.abdelrahman.rafaat.movies.ui.network

import com.abdelrahman.rafaat.movies.model.GenreResponse
import com.abdelrahman.rafaat.movies.model.MovieDetails
import com.abdelrahman.rafaat.movies.model.MovieResponse
import retrofit2.Response



interface RemoteSource {

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