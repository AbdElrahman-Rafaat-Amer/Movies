package com.abdelrahman.rafaat.movies.ui.home.adapter

import android.graphics.Bitmap

interface MovieClickListener {
    fun onMovieClick(movieId: Int)
    fun onImageLongClick(movieImage: Bitmap, movieName: String)
    fun onNameLongClick(movieName: String)
}