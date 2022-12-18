package com.abdelrahman.rafaat.movies.model

data class RegionResponse(
    var regions: List<Region>
)

data class Region(
    var iso_639_1: String,
    var english_name: String,
)