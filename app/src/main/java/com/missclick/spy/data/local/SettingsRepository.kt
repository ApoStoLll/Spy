package com.missclick.spy.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.createDataStore
import com.missclick.spy.data.local.SettingsRepository.Companion.FIRST_LAUNCH
import com.missclick.spy.ui.splash.SplashViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val APP_SETTINGS = "settings"

class SettingsRepository(private val context: Context) {

    private val dataStore: DataStore<Preferences> =
        context.createDataStore(name = APP_SETTINGS)

    val firstLaunch : Flow<Boolean>
        get() = dataStore.data.map {
            return@map it[FIRST_LAUNCH] ?: true
        }

    suspend fun setFirstLaunch(status : Boolean = false){
        dataStore.edit {
            it[FIRST_LAUNCH] = status
        }
    }

    companion object{
        private val FIRST_LAUNCH = booleanPreferencesKey(name = "first")
    }
}