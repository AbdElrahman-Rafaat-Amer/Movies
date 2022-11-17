package com.abdelrahman.rafaat.movies.ui.home.adapter

interface MovieClickListener {
    fun onMovieClick(movieId: Int)
    fun onImageLongClick(imageLink: String, movieName: String)
    fun onNameLongClick(movieName: String)
}