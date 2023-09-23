package com.softcross.uniuniverse.data.model.entities

data class Exam (
    val examID:Int,
    val examType:String,
    val examPercent:Int,
    val examNote:Int,
    val examAverage:Float,
    val userID:Int,
    val lessonID:Int
)