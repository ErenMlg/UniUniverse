package com.softcross.uniuniverse.data.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Day")
data class Day (
    @PrimaryKey(true) @ColumnInfo(name = "dayID") val dayID:Int,
    @ColumnInfo(name = "dayNameTR") val dayNameTR:String,
    @ColumnInfo(name = "dayNameEN") val dayNameEN:String
)