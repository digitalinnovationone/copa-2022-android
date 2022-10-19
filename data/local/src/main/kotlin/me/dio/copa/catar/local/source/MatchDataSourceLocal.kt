package me.dio.copa.catar.local.source

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import me.dio.copa.catar.data.source.MatchesDataSource
import javax.inject.Inject

class MatchDataSourceLocal @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : MatchesDataSource.Local {
    private val key = stringSetPreferencesKey("notification_ids")

    override fun getActiveNotificationIds(): Flow<Set<String>> =
        dataStore.data
            .map { preferences ->
                preferences[key] ?: setOf()
            }

    override suspend fun enableNotificationFor(id: String) {
        dataStore.edit { settings ->
            val currentIds = settings[key] ?: setOf()
            settings[key] = currentIds + id
        }
    }

    override suspend fun disableNotificationFor(id: String) {
        dataStore.edit { settings ->
            val currentIds = settings[key] ?: return@edit
            settings[key] = currentIds - id
        }
    }
}
