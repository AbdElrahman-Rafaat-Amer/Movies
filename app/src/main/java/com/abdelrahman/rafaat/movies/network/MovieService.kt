package com.abdelrahman.rafaat.movies.network

import com.abdelrahman.rafaat.movies.model.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("discover/movie")
    suspend fun getGenreDetails(
        @Query("with_genres") with_genres: String,
        @Query("sort_by") sort_by: String,
        @Query("page") page: String,
        @Query("api_key") api_key: String = "e36c865102e6b09ab8a428344c7b6add"
    ): Response<MovieResponse>

    @GET("discover/movie")
    suspend fun discoverMovie(
        @Query("with_genres") with_genres: String,
        @Query("sort_by") sort_by: String,
        @Query("region") region: String,
        @Query("year") year: String,
        @Query("page") page: String,
      //  @Query("language") language: String,
        @Query("api_key") api_key: String = "e36c865102e6b09ab8a428344c7b6add"
    ): Response<MovieResponse>

    @GET("configuration/countries")
    suspend fun getRegions(
        @Query("api_key") api_key: String = "e36c865102e6b09ab8a428344c7b6add"
    ): Response<List<Region>>

    @GET("search/movie")
    suspend fun searchMovie(
        @Query("query") movieName: String,
        @Query("page") page: Int = 1,
        @Query("api_key") api_key: String = "e36c865102e6b09ab8a428344c7b6add"
    ): Response<MovieResponse>

    @GET("genre/movie/list")
    suspend fun getMovieGenres(
        @Query("api_key") api_key: String = "e36c865102e6b09ab8a428344c7b6add"
    ): Response<GenreResponse>

    @GET("trending/{media_type}/{time_window}")
    suspend fun getTrendingMovie(
        @Path("media_type") media_type: String,
        @Path("time_window") time_window: String,
        @Query("api_key") api_key: String = "e36c865102e6b09ab8a428344c7b6add",
        @Query("page") page: Int = 1
    ): Response<MovieResponse>


    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("api_key") api_key: String = "e36c865102e6b09ab8a428344c7b6add",
        @Query("page") page: Int = 1
    ): Response<MovieResponse>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key") api_key: String = "e36c865102e6b09ab8a428344c7b6add",
        @Query("page") page: Int = 1
    ): Response<MovieResponse>

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") api_key: String = "e36c865102e6b09ab8a428344c7b6add",
        @Query("page") page: Int = 1
    ): Response<MovieResponse>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Long,
        @Path("language") language: String,
        @Query("api_key") api_key: String = "e36c865102e6b09ab8a428344c7b6add",
        @Query("append_to_response") append_to_response: String = "videos,images,keywords"
    ): Response<MovieDetails>

}