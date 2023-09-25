package com.softcross.uniuniverse.data.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "ToDoWork",
    foreignKeys = [
        ForeignKey(
            parentColumns = arrayOf("userID"),
            childColumns = arrayOf("userID"),
            entity = User::class
        )
    ]
)
data class ToDoWork(
    @PrimaryKey(true) @ColumnInfo(name = "workID") val workID: Int,
    @ColumnInfo(name = "workName") val workName: String,
    @ColumnInfo(name = "workProgress") val workProgress: Int,
    @ColumnInfo(name = "workTime") val workTime: String,
    @ColumnInfo(name = "userID", index = true) val userID: Int
)