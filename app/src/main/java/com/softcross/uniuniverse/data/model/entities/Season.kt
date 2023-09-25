package com.softcross.uniuniverse.data.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "Season",
    foreignKeys = [ForeignKey(
        parentColumns = arrayOf("userID"),
        childColumns = arrayOf("userID"),
        entity = User::class
    )])
data class Season(
    @PrimaryKey(true) @ColumnInfo(name = "seasonID") val seasonID: Int,
    @ColumnInfo(name = "seasonName") val seasonName: String,
    @ColumnInfo(name = "seasonAverage") val seasonAverage: Float,
    @ColumnInfo(name = "userID", index = true) val userID: Int
)