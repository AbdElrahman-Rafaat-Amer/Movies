package com.abdelrahman.rafaat.movies.model

import com.abdelrahman.rafaat.movies.ui.network.RemoteSource
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
        sortBy: String,
        page: String
    ): Response<MovieResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun searchMovie(movieName: String): Response<MovieResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun getMovieGenres(): Response<GenreResponse> {
        return remoteSource.getMovieGenres()
    }

    override suspend fun getTrendingMovie(
        mediaType: String,
        timeWindow: String
    ): Response<MovieResponse> {
        return remoteSource.getTrendingMovie(mediaType, timeWindow)
    }

    override suspend fun getUpcomingMovies(): Response<MovieResponse> {
        return remoteSource.getUpcomingMovies()
    }

    override suspend fun getTopRatedMovies(): Response<MovieResponse> {
        return remoteSource.getTopRatedMovies()
    }

    override suspend fun getPopularMovies(): Response<MovieResponse> {
        return remoteSource.getPopularMovies()
    }

    override suspend fun getMovieDetails(movieId: Long): Response<MovieDetails> {
        TODO("Not yet implemented")
    }


}