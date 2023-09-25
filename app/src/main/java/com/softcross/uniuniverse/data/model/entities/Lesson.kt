package com.softcross.uniuniverse.data.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "Lesson", foreignKeys = [
        ForeignKey(
            parentColumns = arrayOf("userID"),
            childColumns = arrayOf("userID"),
            entity = User::class
        ),
        ForeignKey(
            parentColumns = arrayOf("seasonID"),
            childColumns = arrayOf("seasonID"),
            entity = Season::class
        )
    ]
)
data class Lesson(
    @PrimaryKey(true) @ColumnInfo(name = "lessonID") val lessonID: Int,
    @ColumnInfo(name = "lessonName") val lessonName: String,
    @ColumnInfo(name = "lessonNote") val lessonNote: Int,
    @ColumnInfo(name = "lessonCredit") val lessonCredit: Int,
    @ColumnInfo(name = "lessonAverage") val lessonAverage: Float,
    @ColumnInfo(name = "userID", index = true) val userID: Int,
    @ColumnInfo(name = "seasonID", index = true) val seasonID: Int
)