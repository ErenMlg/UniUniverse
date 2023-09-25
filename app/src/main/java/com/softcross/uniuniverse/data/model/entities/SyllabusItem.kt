package com.softcross.uniuniverse.data.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "SyllabusItem",
    foreignKeys = [
        ForeignKey(
            parentColumns = arrayOf("userID"),
            childColumns = arrayOf("userID"),
            entity = User::class
        ),
        ForeignKey(
            parentColumns = arrayOf("dayID"),
            childColumns = arrayOf("dayID"),
            entity = Day::class
        )
    ]
)
data class SyllabusItem(
    @PrimaryKey(true) @ColumnInfo(name = "itemID") val itemID: Int,
    @ColumnInfo(name = "itemName") val itemName: String,
    @ColumnInfo(name = "itemStartHour") val itemStartHour: String,
    @ColumnInfo(name = "itemEndHour") val itemEndHour: String,
    @ColumnInfo(name = "itemPlace") val itemPlace: String,
    @ColumnInfo(name = "dayID", index = true) val dayID: Int,
    @ColumnInfo(name = "userID", index = true) val userID: Int
)