package com.abdelrahman.rafaat.movies.network

import com.abdelrahman.rafaat.movies.model.MovieDetails
import com.abdelrahman.rafaat.movies.model.MovieQuery
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
        sort_by: String,
        page: String
    ): Response<MovieQuery> {
        TODO("Not yet implemented")
    }

    override suspend fun getTrendingMovie(
        mediaType: String,
        timeWindow: String
    ): Response<MovieQuery> {
        TODO("Not yet implemented")
    }

    override suspend fun searchMovie(movieName: String): Response<MovieQuery> {
        TODO("Not yet implemented")
    }

    override suspend fun getMovieDetails(movieId: Long): Response<MovieDetails> {
        TODO("Not yet implemented")
    }
}