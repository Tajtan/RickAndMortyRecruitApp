package com.example.rickandmortyrecruitapp.data

data class CharactersResponse(
    val info: Pages,
    val results: List<Character>
)

// Data class to fetch characters from API as they are defined in the API documentation.
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

// Data class of (last known) location for Character data class. Needed because in response from API the location is an object.
data class Location(
    val name: String
)

// Data class of origin (location) for Character data class. Needed because in response from API the origin is an object.
data class Origin(
    val name: String
)