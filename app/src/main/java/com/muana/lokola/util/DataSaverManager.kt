package com.muana.lokola.util

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

// Extension property for DataStore
private val Context.dataStore by preferencesDataStore(name = "lokoladata")

class DataSaverManager(private val context: Context) {

    companion object {
        val DATA_SAVER_KEY = booleanPreferencesKey("data_saver_enabled")
    }

    // Get Data Saver status as Flow
    val isDataSaverEnabled: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[DATA_SAVER_KEY] ?: true // Enabled by default
        }

    // Toggle Data Saver
    suspend fun setDataSaver(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[DATA_SAVER_KEY] = enabled
        }
    }

    // Get current status (suspend function for one-time read)
    suspend fun getDataSaverStatus(): Boolean {
        return context.dataStore.data
            .map { preferences ->
                preferences[DATA_SAVER_KEY] ?: true
            }.first()
    }
}
