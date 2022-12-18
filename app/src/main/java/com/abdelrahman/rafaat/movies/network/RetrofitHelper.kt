package com.abdelrahman.rafaat.movies.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    private const val API_URL = "https://api.themoviedb.org/3/"
    const val BASE_IMAGE_URL_API = "https://image.tmdb.org/t/p/w185_and_h278_bestv2/"
    const val BASE_IMAGE_W500_URL_API = "https://image.tmdb.org/t/p/w500/"
    const val BASE_IMAGE_URL_ORIGINAL_API = "https://image.tmdb.org/t/p/original/"

    fun getClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }


}