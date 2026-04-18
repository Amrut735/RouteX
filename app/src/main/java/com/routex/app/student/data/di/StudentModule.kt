package com.routex.app.student.data.di

import com.routex.app.student.data.repository.StudentRepositoryImpl
import com.routex.app.student.domain.repository.StudentRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class StudentModule {

    @Binds
    @Singleton
    abstract fun bindStudentRepository(impl: StudentRepositoryImpl): StudentRepository
}
