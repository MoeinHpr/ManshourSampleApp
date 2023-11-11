package com.hpr.data.module.cinema

import com.hpr.data.api.CinemaApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class CinemaModule {

    @Provides
    fun provideMapApi(retrofit: Retrofit): CinemaApi =
        retrofit.create(CinemaApi::class.java)

}