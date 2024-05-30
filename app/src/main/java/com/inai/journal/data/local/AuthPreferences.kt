package com.inai.journal.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.inai.journal.platform.constant.Constants
import com.inai.journal.platform.constant.Constants.AUTH_KEY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class AuthPreferences(private val dataStore: DataStore<Preferences>) {

    suspend fun saveAuthToken(loginToken: String) {
        dataStore.edit { pref ->
            pref[AUTH_KEY] = setOf(loginToken)
        }
    }

    suspend fun getAuthToken(): String? {
        val preferences = dataStore.data.first()
        val tokens = preferences[AUTH_KEY]
        return tokens?.firstOrNull()
    }
}


object DataStoreManager {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "auth_prefs")

    @Volatile
    private var INSTANCE: DataStore<Preferences>? = null

    fun getInstance(context: Context): DataStore<Preferences> {
        return INSTANCE ?: synchronized(this) {
            val instance = context.dataStore
            INSTANCE = instance
            instance
        }
    }
}
