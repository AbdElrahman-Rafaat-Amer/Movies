package com.abdelrahman.rafaat.movies.model

import com.abdelrahman.rafaat.movies.R

enum class SortBy {
    Popularity("popularity.asc", "popularity.desc", R.string.popularity, true),
    ReleaseDate("release_date.asc", "release_date.desc", R.string.release_date, true),
    Revenue("revenue.asc", "revenue.desc", R.string.revenue, true),
    OriginalTitle("original_title.asc", "original_title.desc", R.string.original_title, true),
    VoteAverage("vote_average.asc", "vote_average.desc", R.string.vote_average, true),
    VoteCount("vote_count.asc", "vote_count.desc", R.string.vote_count, true);

    // custom properties with default values
    var value: Int? = null
    var ascending: String? = null
    var descending: String? = null
    var isDescending: Boolean? = null

    constructor()

    // custom constructors
    constructor(
        ascending: String,
        descending: String,
        value: Int,
        isDescending: Boolean
    ) {
        this.value = value
        this.ascending = ascending
        this.descending = descending
        this.isDescending = isDescending
    }
}