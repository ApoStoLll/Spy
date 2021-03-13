package com.missclick.spy.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
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

    val players : Flow<Int>
        get() = dataStore.data.map {
            return@map it[PLAYERS] ?: 4
        }

    val spies : Flow<Int>
        get() = dataStore.data.map {
            return@map it[SPIES] ?: 1
        }

    val timer : Flow<Int>
        get() = dataStore.data.map {
            return@map it[TIMER] ?: 3
        }

    val set : Flow<String>
        get() = dataStore.data.map {
            return@map it[SET] ?: "Basic"
        }

    suspend fun setPlayers(players : Int){
        dataStore.edit {
            it[PLAYERS] = players
        }
    }

    suspend fun setSpies(spies : Int){
        dataStore.edit {
            it[SPIES] = spies
        }
    }

    suspend fun setTimer(timer : Int){
        dataStore.edit {
            it[TIMER] = timer
        }
    }

    suspend fun setSet(set : String){
        dataStore.edit {
            it[SET] = set
        }
    }

    suspend fun setFirstLaunch(status : Boolean = false){
        dataStore.edit {
            it[FIRST_LAUNCH] = status
        }
    }

    companion object{
        private val FIRST_LAUNCH = booleanPreferencesKey(name = "first")
        private val PLAYERS = intPreferencesKey(name = "players")
        private val SPIES = intPreferencesKey(name = "spies")
        private val TIMER = intPreferencesKey(name = "timer")
        private val SET = stringPreferencesKey(name = "set")
    }
}