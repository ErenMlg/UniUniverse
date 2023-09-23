package com.softcross.uniuniverse.data.model.entities

data class SyllabusItem (
    val itemID:Int,
    val itemName:String,
    val itemStartHour:String,
    val itemEndHour:String,
    val itemPlace:String,
    val dayID:Int,
    val userID:Int
)