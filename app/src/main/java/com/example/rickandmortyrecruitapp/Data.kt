package com.example.rickandmortyrecruitapp

data class Character(
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: Any,
    val location: Any,
    val image: String,
    val episode: List<String>
)

data class CharactersResponse(
    val results: List<Character>
)