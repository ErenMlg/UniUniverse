package com.softcross.uniuniverse.data.model.entities

import android.graphics.Bitmap

data class User(
    val userID:Int,
    val userName:String,
    val userSurname:String,
    val userPhoto:Bitmap
)