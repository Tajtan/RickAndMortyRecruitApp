package com.example.rickandmortyrecruitapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.rickandmortyrecruitapp.ui.theme.RickAndMortyRecruitAppTheme

// Main Activity class to enable integration with Jetpack Compose, perform necessary initialization of the activity, apply App Theme, create a Nav Controller for navigation within the app and call RickAndMortyApp which is a start of the app.
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            RickAndMortyRecruitAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RickAndMortyApp(navController = navController)
                }
            }
        }
    }
}