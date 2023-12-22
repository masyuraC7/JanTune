package com.jantune.heartdisease.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "userPreferences")

class UserPreferences @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {

    private val userToken = stringPreferencesKey("userToken")
    private val userId = intPreferencesKey("userId")

    fun getUserToken(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[userToken] ?: ""
        }
    }

    fun getUserId(): Flow<Int> {
        return dataStore.data.map { preferences ->
            preferences[userId] ?: 0
        }
    }

    suspend fun saveUserSession(token: String, id: Int) {
        dataStore.edit { preferences ->
            preferences[userToken] = token
            preferences[userId] = id
        }
    }

    suspend fun deleteUserSession() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}