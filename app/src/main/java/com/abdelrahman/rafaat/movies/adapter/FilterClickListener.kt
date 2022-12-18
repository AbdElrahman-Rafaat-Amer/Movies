package com.abdelrahman.rafaat.movies.adapter

interface FilterClickListener {
    fun onGenreSelected(genre: String)
    fun onRegionSelected(region: String)
    fun onSortBySelected(sortBy: String)
    fun onYearSelected(year: String)
}