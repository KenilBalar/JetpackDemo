package com.example.jetpackpractice.utils

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.jetpackpractice.utils.utils.CREDENTIALS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = CREDENTIALS)

class Preferences : AppCompatActivity() {

    suspend fun readString(context: Context, key: String) : String {
        context.dataStore.data.map { preferences ->
                // No type safety.
                preferences[stringPreferencesKey(key)] ?: ""
            }
        val preferences = dataStore.data.first()
        return preferences[stringPreferencesKey(key)] ?: ""
    }

    suspend fun readBoolean(context: Context, key: String) : Boolean {
        context.dataStore.data.map { preferences ->
            // No type safety.
            preferences[stringPreferencesKey(key)] ?: ""
        }
        val preferences = dataStore.data.first()
        return preferences[booleanPreferencesKey(key)] ?: false
    }

    suspend fun writeString(context: Context, key: String, value: String) {
        context.dataStore.edit { settings ->
            settings[stringPreferencesKey(key)] = value
        }
    }
    suspend fun writeBoolean(context: Context, key: String, value: Boolean) {
        context.dataStore.edit { settings ->
            settings[booleanPreferencesKey(key)] = value
        }
    }
}