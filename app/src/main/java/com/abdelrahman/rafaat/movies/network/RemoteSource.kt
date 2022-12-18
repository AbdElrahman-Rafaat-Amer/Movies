package com.abdelrahman.rafaat.movies.network

import com.abdelrahman.rafaat.movies.model.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface RemoteSource {

    suspend fun getCategoryMovies(
        genres: String,
        sortBy: String,
        page: String
    ): Response<MovieResponse>

    suspend fun searchMovie(
        movieName: String, page: Int
    ): Response<MovieResponse>

    suspend fun discoverMovie(
        genre: String,
        sortBy: String,
        region: String,
        year: String,
        page: Int
    ): Response<MovieResponse>

    suspend fun getMovieGenres(): Response<GenreResponse>

    suspend fun getRegions(): Response<List<Region>>

    suspend fun getTrendingMovie(
        mediaType: String,
        timeWindow: String
    ): Response<MovieResponse>


    suspend fun getUpcomingMovies(): Response<MovieResponse>

    suspend fun getTopRatedMovies(): Response<MovieResponse>

    suspend fun getPopularMovies(): Response<MovieResponse>

    suspend fun getMovieDetails(movieId: Long): Response<MovieDetails>


}