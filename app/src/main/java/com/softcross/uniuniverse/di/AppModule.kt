package com.softcross.uniuniverse.di

import android.content.Context
import com.softcross.uniuniverse.data.source.DatabaseObject
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideUserWorksDao(@ApplicationContext context: Context) =
        DatabaseObject.getInstance(context).getUserWorksDao()

}