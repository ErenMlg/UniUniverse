package com.softcross.uniuniverse.di

import com.softcross.uniuniverse.data.repository.UserWorksRepository
import com.softcross.uniuniverse.data.source.UserWorksDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideUserWorksRepository(userWorksDao: UserWorksDao): UserWorksRepository = UserWorksRepository(userWorksDao)

}