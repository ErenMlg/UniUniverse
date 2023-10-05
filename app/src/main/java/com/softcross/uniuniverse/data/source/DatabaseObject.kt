package com.softcross.uniuniverse.data.source

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.softcross.uniuniverse.common.util.Converters
import com.softcross.uniuniverse.data.model.entities.Day
import com.softcross.uniuniverse.data.model.entities.Exam
import com.softcross.uniuniverse.data.model.entities.Lesson
import com.softcross.uniuniverse.data.model.entities.Season
import com.softcross.uniuniverse.data.model.entities.SyllabusItem
import com.softcross.uniuniverse.data.model.entities.ToDoWork
import com.softcross.uniuniverse.data.model.entities.User
import dagger.hilt.android.qualifiers.ApplicationContext

@Database(
    entities = [Day::class, Exam::class, Lesson::class, Season::class, SyllabusItem::class, ToDoWork::class, User::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class DatabaseObject : RoomDatabase() {
    abstract fun getUserWorksDao(): UserWorksDao

}