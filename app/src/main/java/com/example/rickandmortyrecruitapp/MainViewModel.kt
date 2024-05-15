package com.example.rickandmortyrecruitapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyrecruitapp.data.CharacterWithEpisodes
import com.example.rickandmortyrecruitapp.data.Episode
import com.example.rickandmortyrecruitapp.data.Character
import com.example.rickandmortyrecruitapp.data.CharactersMapper
import com.example.rickandmortyrecruitapp.networking.Networking
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

// Main ViewModel class responsible for managing UI-related data.
class MainViewModel: ViewModel() {
    // Property which holds the state of characters in the UI.
    private val _charactersState = mutableStateOf(CharactersState())
    val charactersState: State<CharactersState> = _charactersState

    private val charactersMapper = CharactersMapper()
    private val networking = Networking()

    // Init block to immediately start fetching data upon initialization, meaning at the launch of the App.
    init {
        fetchData()
    }

    // Main function that launches a Coroutine Scope to fetch data and change characterState to finish loading and fill either list of Characters.
    private fun fetchData() {
        viewModelScope.launch {
            try {
                val (characters, episodes) = fetchCharactersAndEpisodes()
                val charactersWithEpisodes = charactersMapper.mapToCharactersWithEpisodes(characters, episodes)

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

    // Function called by fetchData to asynchronously fetch characters and episodes lists as Pair.
    private suspend fun fetchCharactersAndEpisodes(): Pair<List<Character>, List<Episode>> {
        val charactersDeferred = viewModelScope.async { networking.fetchAllCharacters() }
        val episodesDeferred = viewModelScope.async { networking.fetchAllEpisodes() }
        return charactersDeferred.await() to episodesDeferred.await()
    }

    // Function called by retry button on characters list screen when fetching data sends an error.
    // It resets characters state and fetches data again.
    fun retryFetchCharacters() {
        _charactersState.value = CharactersState(loading = true, list = emptyList(), error = null)
        fetchData()
    }

    // Function that changes displayed Character to a character selected by user to details screen.
    // Used to display selected character from list in details screen.
    fun pickCharacter(character: CharacterWithEpisodes) {
        val currentState = _charactersState.value
        _charactersState.value = currentState.copy(displayedCharacter = character)
    }

    // Data class to hold characters state. Loading when data is fetched and mapped, list to hold characters list, error if it occurs and displayed Character for details screen.
    data class CharactersState(
        val loading: Boolean = true,
        val list: List<CharacterWithEpisodes> = emptyList(),
        val error: String? = null,
        val displayedCharacter: CharacterWithEpisodes? = null
    )
}