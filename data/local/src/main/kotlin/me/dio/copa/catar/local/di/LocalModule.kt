package me.dio.copa.catar.local.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.Binds
import dagger.Module
import dagger.Provides
import me.dio.copa.catar.local.source.MatchDataSourceLocal
import me.dio.copa.catar.data.source.MatchesDataSource

private const val PREFERENCES_NAME = "notifications_prefs"

@Module
interface LocalModule {
    @Binds
    fun providesMatchDataSourceLocal(impl: MatchDataSourceLocal): MatchesDataSource.Local

    companion object {
        private val Context.dataStore by preferencesDataStore(name = PREFERENCES_NAME)

        @Provides
        fun providesDataStore(context: Context): DataStore<Preferences> =
            context.dataStore
    }
}
