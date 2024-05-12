package com.example.rickandmortyrecruitapp

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize
import java.util.UUID

data class Character(
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

@Parcelize
data class Episode(
    val name: String,
    val air_date: String,
    val episode: String,
    val url: String
) : Parcelable

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
    val episode: List<Episode>
) : Parcelable

@Parcelize
data class Location(
    val name: String
): Parcelable

@Parcelize
data class Origin(
    val name: String
): Parcelable
