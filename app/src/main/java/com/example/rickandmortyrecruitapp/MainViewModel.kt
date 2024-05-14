package com.example.rickandmortyrecruitapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _charactersState = mutableStateOf(CharactersState())
    val charactersState: State<CharactersState> = _charactersState

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            try {
                val (characters, episodes) = fetchCharactersAndEpisodes()
                val charactersWithEpisodes = mapToCharactersWithEpisodes(characters, episodes)

                _charactersState.value = _charactersState.value.copy(
                    list = charactersWithEpisodes,
                    loading = false,
                    error = null
                )
            } catch (e: Exception) {
                _charactersState.value = _charactersState.value.copy(
                    loading = false,
                    error = "Error fetching data: ${e.message}"
                )
            }
        }
    }

    private suspend fun fetchCharactersAndEpisodes(): Pair<List<Character>, List<Episode>> {
        val charactersDeferred = viewModelScope.async { fetchAllCharacters() }
        val episodesDeferred = viewModelScope.async { fetchAllEpisodes() }
        return charactersDeferred.await() to episodesDeferred.await()
    }

    private suspend fun fetchAllCharacters(): List<Character> {
        val allCharacters: MutableList<Character> = mutableListOf()
        var currentPage = 1
        var totalPages = 1

        while (currentPage <= totalPages) {
            val response = characterService.getCharacters(currentPage)
            totalPages = response.info.pages

            allCharacters.addAll(response.results)

            currentPage++
        }

        return allCharacters
    }

    private suspend fun fetchAllEpisodes(): List<Episode> {
        val allEpisodes: MutableList<Episode> = mutableListOf()
        var currentPage = 1
        var totalPages = 1

        while (currentPage <= totalPages) {
            val response = characterService.getEpisodes(currentPage)
            totalPages = response.info.pages

            allEpisodes.addAll(response.results)

            currentPage++
        }

        return allEpisodes
    }

    private fun mapToCharactersWithEpisodes(
        characters: List<Character>,
        episodes: List<Episode>
    ): List<CharacterWithEpisodes> {
        return characters.map { character ->
            val episodesForCharacter = episodes.filter { episode ->
                character.episode.contains(episode.url)
            }.map { episode ->
                EpisodeWithoutUrl(
                    name = episode.name,
                    airDate = episode.air_date,
                    episode = formatEpisode(episode.episode)
                )
            }
            CharacterWithEpisodes(
                id = character.id,
                name = character.name,
                status = character.status,
                species = character.species,
                type = character.type,
                gender = character.gender,
                origin = character.origin,
                location = character.location,
                image = character.image,
                episode = episodesForCharacter
            )
        }
    }

    fun retryFetchCharacters() {
        _charactersState.value = CharactersState(loading = true, list = emptyList(), error = null)
        fetchData()
    }

    private fun formatEpisode(episode: String): String {
        val parts = episode.split("E")
        return "S.${parts[0].removePrefix("S").toInt()} E.${parts[1].toInt()}"
    }

    fun pickCharacter(character: CharacterWithEpisodes) {
        val currentState = _charactersState.value
        _charactersState.value = currentState.copy(displayedCharacter = character)
    }

    data class CharactersState(
        val loading: Boolean = true,
        val list: List<CharacterWithEpisodes> = emptyList(),
        val error: String? = null,
        val displayedCharacter: CharacterWithEpisodes? = null
    )
}