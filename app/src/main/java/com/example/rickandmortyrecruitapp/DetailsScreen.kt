package com.example.rickandmortyrecruitapp

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(character: CharacterWithEpisodes, onBackNavClicked: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.details_title_top_bar))
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onBackNavClicked()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back to characters list"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState()),
        ) {
            Row {
                Image(
                    painter = rememberAsyncImagePainter(model = character.image),
                    contentDescription = "Character image",
                    modifier = Modifier
                        .size(164.dp)
                        .aspectRatio(1f)
                        .padding(8.dp)
                        .clip(RoundedCornerShape(20)),
                    alignment = Alignment.CenterEnd,
                    contentScale = ContentScale.Crop
                )
                Column(
                    modifier = Modifier.fillMaxSize().padding(horizontal = 8.dp)
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .border(
                                width = 2.dp,
                                shape = RoundedCornerShape(20),
                                color = statusColor(status = character.status)
                            )
                    ) {
                        Text(
                            text = "Status: ${character.status}",
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier
                                .padding(vertical = 2.dp, horizontal = 15.dp)
                        )
                    }
                    Text(text = character.name, style = MaterialTheme.typography.bodyLarge, modifier = Modifier
                        .wrapContentWidth())
                }
            }
            ProfileText(label = "Last known location", attribute = character.location.name)
            ProfileText(label = "Episode count", attribute = character.episode.size.toString())
            EpisodesLazyRow(character.episode)
            Spacer(modifier = Modifier.height(16.dp))
            ProfileText(label = "Species", attribute = character.species)
            if (character.type.isNotEmpty()) {
                ProfileText(label = "Type", attribute = character.type)
            }
            ProfileText(label = "Gender", attribute = character.gender)
            ProfileText(label = "Origin", attribute = character.origin.name)
        }
    }
}

@Composable
fun ProfileText(label: String, attribute: String) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.titleSmall
        )
        Row(

        ) {
            Text(
                text = attribute,
                style = MaterialTheme.typography.bodyMedium
            )
            if (label == "Gender") {
                Icon(
                    painter =
                    when(attribute) {
                        "Male" -> painterResource(id = R.drawable.baseline_male_24)
                        "Female" -> painterResource(id = R.drawable.baseline_female_24)
                        "Genderless" -> painterResource(id = R.drawable.baseline_water_drop_24)
                        else -> painterResource(id = R.drawable.baseline_question_mark_24)
                    },
                    contentDescription = "",
                    modifier = Modifier
                        .size(25.dp)
                        .align(Alignment.CenterVertically)
                )

            }
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
fun EpisodesLazyRow(episodes: List<EpisodeWithoutUrl>) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp, start = 8.dp)
    ) {
        items(episodes, key = { it.episode }) {
            episode ->
            EpisodeItem(episode = episode)
        }
    }
}

@Composable
fun EpisodeItem(episode: EpisodeWithoutUrl) {
    Card(
        modifier = Modifier
            .padding(end = 10.dp)
            .fillMaxSize(),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = episode.episode, style = MaterialTheme.typography.titleLarge)
            Column(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
            ) {
                Text(text = episode.name, style = MaterialTheme.typography.bodySmall)
                Text(text = episode.air_date, style = MaterialTheme.typography.bodySmall)
            }

        }
    }
}

@Composable
fun statusColor(status: String): Color {
    val darkTheme: Boolean = isSystemInDarkTheme()
    Log.e("dark theme", darkTheme.toString())

    return if (darkTheme) {
        when (status) {
            "Alive" -> colorResource(id = R.color.green_dark_theme)
            "Dead" -> colorResource(id = R.color.red_dark_theme)
            else -> colorResource(id = R.color.gray_dark_theme)
        }
    } else {
        when (status) {
            "Alive" -> colorResource(id = R.color.green_light_theme)
            "Dead" -> colorResource(id = R.color.red_light_theme)
            else -> colorResource(id = R.color.gray_light_theme)
        }
    }
}

@Preview
@Composable
fun Preview() {
    val previewEpisodes = listOf(
        EpisodeWithoutUrl("Pilot", "December 2, 2013", "S01E01"),
        EpisodeWithoutUrl("The Ricklantis Mixup", "September 10, 2017", "S03E07")
    )
    val previewCharacter = CharacterWithEpisodes(1,"Rick Sanchez","Alive","Human","typeTest","Male", Origin("Earth"), Location("Citadel"), "", previewEpisodes)
    DetailsScreen(character = previewCharacter) {}
}