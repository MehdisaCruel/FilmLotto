package com.example.filmlotto

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "favorites")

class FavoriteDataStore(private val context: Context) {

    companion object {
        val FAVORITES_KEY = stringSetPreferencesKey("favorite_movie_ids")
    }

    val favoriteMoviesFlow: Flow<Set<String>> = context.dataStore.data
        .map { preferences -> preferences[FAVORITES_KEY] ?: emptySet() }

    suspend fun addFavorite(id: String) {
        context.dataStore.edit { preferences ->
            val current = preferences[FAVORITES_KEY] ?: emptySet()
            preferences[FAVORITES_KEY] = current + id
        }
    }

    suspend fun removeFavorite(id: String) {
        context.dataStore.edit { preferences ->
            val current = preferences[FAVORITES_KEY] ?: emptySet()
            preferences[FAVORITES_KEY] = current - id
        }
    }
}
