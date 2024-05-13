package com.example.rickandmortyrecruitapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private val _charactersState = mutableStateOf(CharactersState())
    val charactersState: State<CharactersState> = _charactersState

    init {
        fetchCharacters()
    }

    private fun fetchCharacters(){
        viewModelScope.launch {
            try {
                val allCharacters: MutableList<Character> = mutableListOf()
                val allEpisodes: MutableList<Episode> = mutableListOf()
                val allCharactersWithEpisodes: MutableList<CharacterWithEpisodes> = mutableListOf()
                var currentPage = 1
                var totalPages = 1

                while (currentPage <= totalPages) {
                    val response = characterService.getCharacters(currentPage)
                    totalPages = response.info.pages

                    allCharacters.addAll(response.results)

                    currentPage++
                }

                currentPage = 1
                totalPages = 1

                while (currentPage <= totalPages) {
                    val response = characterService.getEpisodes(currentPage)
                    totalPages = response.info.pages

                    allEpisodes.addAll(response.results)

                    currentPage++
                }

                allCharacters.forEach { character ->
                    val episodesForCharacter = allEpisodes.filter { character.episode.contains(it.url) }
                    allCharactersWithEpisodes.add(
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
                    )
                }

                _charactersState.value = _charactersState.value.copy(
                    list = allCharactersWithEpisodes,
                    loading = false,
                    error = null
                )
            } catch (e: Exception) {
                _charactersState.value = _charactersState.value.copy(
                    loading = false,
                    error = "Error fetching Characters: ${e.message}"
                )
            }
        }
    }

    fun retryFetchCharacters() {
        _charactersState.value = CharactersState(loading = true, list = emptyList(), error = null)
        fetchCharacters()
    }

    data class CharactersState(
        val loading: Boolean = true,
        val list: List<CharacterWithEpisodes> = emptyList(),
        val error: String? = null
    )
}