package com.example.rickandmortyrecruitapp.data

class CharactersMapper {
    // Function called by fetchData to map fetched characters and episodes to desired format.
    // It changes Episodes to EpisodesWithoutUrl, Characters to CharactersWithEpisodes, and formats Episode (episode number and season).
    fun mapToCharactersWithEpisodes(
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

    // Function called by mapToCharactersWithEpisodes to format Episode from S01E01 to "S.1 E.1" using regex.
    private fun formatEpisode(episode: String): String {
        val regex = "S\\d+E\\d+"
        if (regex.toRegex().matches(episode)) {
            val parts = episode.split("E")
            return "S.${parts[0].removePrefix("S").toInt()} E.${parts[1].toInt()}"
        } else {
            return episode
        }
    }
}