package com.softcross.uniuniverse.data.model.entities

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")
data class User(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "userID") val userID: Int,
    @ColumnInfo(name = "userName") val userName: String,
    @ColumnInfo(name = "userSurname") val userSurname: String,
    @ColumnInfo(name = "userPhoto", typeAffinity = ColumnInfo.BLOB) val userPhoto: Bitmap
)