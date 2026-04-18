package com.routex.app.maps.data.di

import com.routex.app.maps.data.repository.MapsRepositoryImpl
import com.routex.app.maps.domain.repository.MapsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MapsModule {

    @Binds
    @Singleton
    abstract fun bindMapsRepository(impl: MapsRepositoryImpl): MapsRepository
}
