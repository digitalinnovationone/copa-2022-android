package me.dio.copa.catar.data.di

import dagger.Binds
import dagger.Module
import me.dio.copa.catar.data.repository.MatchesRepositoryImpl
import me.dio.copa.catar.domain.repositories.MatchesRepository

@Module
interface DataModule {

    @Binds
    fun providesMatchesRepository(impl: MatchesRepositoryImpl): MatchesRepository
}
