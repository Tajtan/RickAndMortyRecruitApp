package com.example.rickandmortyrecruitapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun DetailsScreen(character: Character) {
    Column(

    ) {
        Text(text = character.name)
        Text(text = character.status)
        Text(text = character.species)
        Text(text = character.type)
        Text(text = character.gender)
        Text(text = character.origin.name)
        Text(text = character.location.name)

        Image(
            painter = rememberAsyncImagePainter(model = character.image),
            contentDescription = "Character image",
            modifier = Modifier
                .size(90.dp)
                .aspectRatio(1f)
        )

        Text(text = character.episode.toString())
    }
}