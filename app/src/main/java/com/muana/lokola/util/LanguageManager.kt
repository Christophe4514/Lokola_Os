package com.muana.lokola.util

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.first

// DataStore instance
private val Context.dataStore by preferencesDataStore(name = "lokola_prefs")

class LanguageManager(private val context: Context) {

    companion object {
        const val LANGUAGE_FRENCH = "fr"
        const val LANGUAGE_LINGALA = "ling"
        val LANGUAGE_KEY = stringPreferencesKey("selected_language")
    }

    // Get current language as Flow
    val currentLanguage: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[LANGUAGE_KEY] ?: LANGUAGE_FRENCH
        }

    // Save language preference
    suspend fun setLanguage(language: String) {
        context.dataStore.edit { preferences ->
            preferences[LANGUAGE_KEY] = language
        }
    }

    // Get current language (suspend function for one-time read)
    suspend fun getCurrentLanguage(): String {
        return context.dataStore.data
            .map { preferences ->
                preferences[LANGUAGE_KEY] ?: LANGUAGE_FRENCH
            }.first()
    }
}
