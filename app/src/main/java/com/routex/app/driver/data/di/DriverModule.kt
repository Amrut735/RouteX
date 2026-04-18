package com.routex.app.driver.data.di

import com.routex.app.driver.data.repository.DriverRepositoryImpl
import com.routex.app.driver.domain.repository.DriverRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DriverModule {

    @Binds
    @Singleton
    abstract fun bindDriverRepository(impl: DriverRepositoryImpl): DriverRepository
}
