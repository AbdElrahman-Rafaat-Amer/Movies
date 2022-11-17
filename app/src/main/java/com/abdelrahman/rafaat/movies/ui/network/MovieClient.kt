package com.abdelrahman.rafaat.movies.ui.network

import com.abdelrahman.rafaat.movies.model.GenreResponse
import com.abdelrahman.rafaat.movies.model.MovieDetails
import com.abdelrahman.rafaat.movies.model.MovieResponse
import retrofit2.Response

class MovieClient private constructor() : RemoteSource {

    companion object {
        private var instance: MovieClient? = null
        fun getInstance(): MovieClient {
            return instance ?: MovieClient()
        }
    }

    private val retrofitHelper = RetrofitHelper.getClient().create(MovieService::class.java)

    override suspend fun getCategoryMovies(
        genres: String,
        sortBy: String,
        page: String
    ): Response<MovieResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun searchMovie(movieName: String, page: Int): Response<MovieResponse> {
        return retrofitHelper.searchMovie(movieName = movieName, page = page)
    }

    override suspend fun getMovieGenres(): Response<GenreResponse> {
        return retrofitHelper.getMovieGenres()
    }

    override suspend fun getTrendingMovie(
        mediaType: String,
        timeWindow: String
    ): Response<MovieResponse> {
        return retrofitHelper.getTrendingMovie(mediaType, timeWindow)
    }

    override suspend fun getUpcomingMovies(): Response<MovieResponse> {
        return retrofitHelper.getUpcomingMovies()
    }

    override suspend fun getTopRatedMovies(): Response<MovieResponse> {
        return retrofitHelper.getTopRatedMovies()
    }

    override suspend fun getPopularMovies(): Response<MovieResponse> {
        return retrofitHelper.getPopularMovies()
    }


    override suspend fun getMovieDetails(movieId: Long): Response<MovieDetails> {
        TODO("Not yet implemented")
    }
}