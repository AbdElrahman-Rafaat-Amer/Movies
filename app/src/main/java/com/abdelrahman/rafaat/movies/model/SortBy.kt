package com.abdelrahman.rafaat.movies.model

import com.abdelrahman.rafaat.movies.R

enum class SortBy {
    Popularity("popularity.asc", "popularity.desc", R.string.popularity),
    ReleaseDate("release_date.asc", "release_date.desc", R.string.release_date),
    Revenue("revenue.asc", "revenue.desc", R.string.revenue),
    OriginalTitle("original_title.asc", "original_title.desc", R.string.original_title),
    VoteAverage("vote_average.asc", "vote_average.desc", R.string.vote_average),
    VoteCount("vote_count.asc", "vote_count.desc", R.string.vote_count);

    // custom properties with default values
    var value: Int? = null
    var ascending: String? = null
    var descending: String? = null

    private constructor()

    // custom constructors
    private constructor(
        ascending: String,
        descending: String,
        value: Int
    ) {
        this.value = value
        this.ascending = ascending
        this.descending = descending
    }
}