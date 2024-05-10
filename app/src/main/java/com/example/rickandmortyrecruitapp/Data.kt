package com.example.rickandmortyrecruitapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
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
): Parcelable {
    @Parcelize
    data class Location(
        val name: String
    ): Parcelable

    @Parcelize
    data class Origin(
        val name: String
    ): Parcelable
}

data class CharactersResponse(
    val results: List<Character>
)