package com.softcross.uniuniverse.data.model.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.softcross.uniuniverse.data.model.entities.Day
import com.softcross.uniuniverse.data.model.entities.SyllabusItem

data class DayInSyllabusItem(
    @Embedded val day: Day,
    @Relation(
        parentColumn = "dayID",
        entityColumn = "dayID"
    )
    val daysClass: List<SyllabusItem>
)
