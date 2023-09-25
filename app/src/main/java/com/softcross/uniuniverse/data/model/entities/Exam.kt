package com.softcross.uniuniverse.data.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "Exam", foreignKeys = [
        ForeignKey(
            parentColumns = arrayOf("lessonID"),
            childColumns = arrayOf("lessonID"),
            entity = Lesson::class
        ),
        ForeignKey(
            parentColumns = arrayOf("userID"),
            childColumns = arrayOf("userID"),
            entity = User::class
        ),

    ]
)
data class Exam(
    @PrimaryKey(true) @ColumnInfo(name = "examID") val examID: Int,
    @ColumnInfo(name = "examType") val examType: String,
    @ColumnInfo(name = "examPercent") val examPercent: Int,
    @ColumnInfo(name = "examNote") val examNote: Int,
    @ColumnInfo(name = "examAverage") val examAverage: Float,
    @ColumnInfo(name = "userID", index = true) val userID: Int,
    @ColumnInfo(name = "lessonID", index = true) val lessonID: Int
)