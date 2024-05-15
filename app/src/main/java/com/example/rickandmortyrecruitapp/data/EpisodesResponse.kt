package com.example.rickandmortyrecruitapp.data

// Data class to contain list of fetched Episodes from API nad how many pages of Episodes are there to fetch from the API.
data class EpisodeResponse(
    val info: Pages,
    val results: List<Episode>
)

// Data class to fetch episodes from API as they are defined in the API documentation.
data class Episode(
    val name: String,
    val air_date: String,
    val episode: String,
    val url: String
)