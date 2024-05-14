package com.example.rickandmortyrecruitapp

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize

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
@Parcelize
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
) : Parcelable

@Parcelize
data class Location(
    val name: String
): Parcelable

@Parcelize
data class Origin(
    val name: String
): Parcelable

@Parcelize
data class EpisodeWithoutUrl(
    val name: String,
    val air_date: String,
    val episode: String
) : Parcelable