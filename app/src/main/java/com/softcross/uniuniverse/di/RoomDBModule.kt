package com.softcross.uniuniverse.di

import android.content.Context
import androidx.room.Room
import com.softcross.uniuniverse.data.source.DatabaseObject
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomDBModule {

    @Provides
    @Singleton
    fun provideRoomDB(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, DatabaseObject::class.java, "UniUniverse.sqlite").build()

    @Provides
    @Singleton
    fun provideUserWorksDao(databaseObject: DatabaseObject) = databaseObject.getUserWorksDao()


}