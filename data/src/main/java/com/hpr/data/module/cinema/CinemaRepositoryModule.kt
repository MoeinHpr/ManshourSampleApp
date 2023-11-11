package com.hpr.data.module.cinema

import com.hpr.data.repository.cinema.CinemaRepository
import com.hpr.data.repository.cinema.CinemaRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface CinemaRepositoryModule {

    @Binds
    fun provideRepository(mapRepository: CinemaRepositoryImpl) : CinemaRepository
}