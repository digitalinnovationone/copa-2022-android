package me.dio.copa.catar.remote.di

import dagger.Binds
import dagger.Module
import me.dio.copa.catar.data.source.MatchesDataSource
import me.dio.copa.catar.remote.source.MatchDataSourceRemote

@Module
interface RemoteModule {

    @Binds
    fun providesMatchDataSourceRemote(impl: MatchDataSourceRemote): MatchesDataSource.Remote
}
