package com.example.rickandmortyrecruitapp

import androidx.compose.runtime.Immutable

data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: Origin,
    val location: Location,
    val image: String,
    val episode: List<String>
)

data class CharactersResponse(
    val info: Pages,
    val results: List<Character>
)

data class Pages(
    val pages: Int
)

data class Episode(
    val name: String,
    val air_date: String,
    val episode: String,
    val url: String
)

data class EpisodeResponse(
    val info: Pages,
    val results: List<Episode>
)

@Immutable
data class CharacterWithEpisodes(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: Origin,
    val location: Location,
    val image: String,
    val episode: List<EpisodeWithoutUrl>
)

data class Location(
    val name: String
)

data class Origin(
    val name: String
)

data class EpisodeWithoutUrl(
    val name: String,
    val airDate: String,
    val episode: String
)