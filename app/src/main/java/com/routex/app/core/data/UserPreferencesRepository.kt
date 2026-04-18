package com.routex.app.core.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPreferencesRepository @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) {
    companion object {
        private val KEY_DARK_THEME         = booleanPreferencesKey("dark_theme")
        private val KEY_SEEN_ONBOARDING    = booleanPreferencesKey("seen_onboarding")
        private val KEY_BOARDING_ROUTE_ID  = stringPreferencesKey("boarding_route_id")
        private val KEY_BOARDING_STOP_ID   = stringPreferencesKey("boarding_stop_id")
        private val KEY_BOARDING_STOP_NAME = stringPreferencesKey("boarding_stop_name")
    }

    val isDarkTheme: Flow<Boolean> =
        dataStore.data.map { it[KEY_DARK_THEME] ?: false }

    val hasSeenOnboarding: Flow<Boolean> =
        dataStore.data.map { it[KEY_SEEN_ONBOARDING] ?: false }

    /** Currently selected boarding route ID, or empty string if none set. */
    val boardingRouteId: Flow<String> =
        dataStore.data.map { it[KEY_BOARDING_ROUTE_ID] ?: "" }

    /** Currently selected boarding stop ID, or empty string if none set. */
    val boardingStopId: Flow<String> =
        dataStore.data.map { it[KEY_BOARDING_STOP_ID] ?: "" }

    /** Human-readable name of the selected stop (for display). */
    val boardingStopName: Flow<String> =
        dataStore.data.map { it[KEY_BOARDING_STOP_NAME] ?: "" }

    suspend fun setDarkTheme(isDark: Boolean) {
        dataStore.edit { it[KEY_DARK_THEME] = isDark }
    }

    suspend fun setHasSeenOnboarding(seen: Boolean) {
        dataStore.edit { it[KEY_SEEN_ONBOARDING] = seen }
    }

    suspend fun setBoardingStop(routeId: String, stopId: String, stopName: String) {
        dataStore.edit { prefs ->
            prefs[KEY_BOARDING_ROUTE_ID]  = routeId
            prefs[KEY_BOARDING_STOP_ID]   = stopId
            prefs[KEY_BOARDING_STOP_NAME] = stopName
        }
    }

    suspend fun clearBoardingStop() {
        dataStore.edit { prefs ->
            prefs.remove(KEY_BOARDING_ROUTE_ID)
            prefs.remove(KEY_BOARDING_STOP_ID)
            prefs.remove(KEY_BOARDING_STOP_NAME)
        }
    }
}
