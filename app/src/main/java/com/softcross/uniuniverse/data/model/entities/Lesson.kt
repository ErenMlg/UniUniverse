package com.softcross.uniuniverse.data.model.entities

data class Lesson (
    val lessonID:Int,
    val lessonName:String,
    val lessonNote:Int,
    val lessonCredit:Int,
    val lessonAverage:Float,
    val userID:Int,
    val seasonID:Int
)