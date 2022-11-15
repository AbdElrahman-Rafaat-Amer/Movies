package com.abdelrahman.rafaat.movies.network

import com.abdelrahman.rafaat.movies.model.MovieDetails
import com.abdelrahman.rafaat.movies.model.MovieQuery
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("discover/movie")
    suspend fun getCategoryMovies(
        @Query("with_genres") with_genres: String,
        @Query("sort_by") sort_by: String,
        @Query("page") page: String,
        @Query("api_key") api_key: String = "e36c865102e6b09ab8a428344c7b6add"
    ): Response<MovieQuery>

    @GET("trending/{media_type}/{time_window}")
    suspend fun getTrendingMovie(
        @Path("media_type") media_type: String,
        @Path("time_window") time_window: String,
        @Query("api_key") api_key: String = "e36c865102e6b09ab8a428344c7b6add"
    ): Response<MovieQuery>

    @GET("search/movie")
    suspend fun searchMovie(
        @Query("query") query: String,
        @Query("api_key") api_key: String = "e36c865102e6b09ab8a428344c7b6add"
    ): Response<MovieQuery>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Long,
        @Query("api_key") api_key: String = "e36c865102e6b09ab8a428344c7b6add",
        @Query("append_to_response") append_to_response: String = "videos"
    ): Response<MovieDetails>


}