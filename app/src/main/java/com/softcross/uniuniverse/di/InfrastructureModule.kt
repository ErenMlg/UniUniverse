package com.softcross.uniuniverse.di

import android.content.Context
import com.softcross.uniuniverse.domain.usecase.ValidateStrings
import com.softcross.uniuniverse.infrastructure.StringResourceProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InfrastructureModule {

    @Provides
    @Singleton
    fun provideStringResourceProvider(@ApplicationContext context: Context) = StringResourceProvider(context)

    @Provides
    @Singleton
    fun provideValidateString(resourceProvider: StringResourceProvider) = ValidateStrings(resourceProvider)

}