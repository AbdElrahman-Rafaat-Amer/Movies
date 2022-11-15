package com.abdelrahman.rafaat.movies.model

import com.abdelrahman.rafaat.movies.network.RemoteSource
import retrofit2.Response

class Repository private constructor(private var remoteSource: RemoteSource) : RepositoryInterface {

    companion object {
        private var weatherRepo: Repository? = null
        fun getInstance(
            remoteSource: RemoteSource
        ): Repository {

            return weatherRepo ?: Repository(remoteSource)
        }
    }

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