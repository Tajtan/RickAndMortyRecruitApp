package com.example.rickandmortyrecruitapp.data

import androidx.compose.runtime.Immutable

// Data class to contain list of Characters in the app. The episodes are changes from strings containing episode url to Episode Without Url data class to use in details screen.
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

// Data class to hold modified Episode for less data used to display Character details.
data class EpisodeWithoutUrl(
    val name: String,
    val airDate: String,
    val episode: String
)