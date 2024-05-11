package com.example.rickandmortyrecruitapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
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
                .verticalScroll(rememberScrollState())
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .border(
                        width = 2.dp,
                        shape = RoundedCornerShape(20),
                        color = when(character.status) {
                            "Alive" -> Color.Green
                            "Dead" -> Color.Red
                            else -> Color.Gray
                        }
                    )
            ) {
                Text(
                    text = "Status: ${character.status}",
                    modifier = Modifier
                        .padding(5.dp)
                )
            }
            Text(text = character.name, modifier = Modifier.padding(5.dp))
            Image(
                painter = rememberAsyncImagePainter(model = character.image),
                contentDescription = "Character image",
                modifier = Modifier
                    .size(128.dp)
                    .aspectRatio(1f)
                    .padding(5.dp),
                alignment = Alignment.CenterEnd
            )
            ProfileText(label = "Last known location", attribute = character.location.name)
            ProfileText(label = "Species", attribute = character.species)
            if (character.type.isNotEmpty()) {
                ProfileText(label = "Type", attribute = character.type)
            }
            ProfileText(label = "Gender", attribute = character.gender)
            ProfileText(label = "Origin", attribute = character.origin.name)
            Text(text = character.episode.toString())
        }
    }
}

@Composable
fun ProfileText(label: String, attribute: String) {
    Column(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.titleSmall
        )
        Text(
            text = attribute,
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(10.dp))
    }
}