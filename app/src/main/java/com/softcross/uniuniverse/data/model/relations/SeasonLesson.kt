package com.softcross.uniuniverse.data.model.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.softcross.uniuniverse.data.model.entities.Lesson
import com.softcross.uniuniverse.data.model.entities.Season

data class SeasonLesson(
    @Embedded val season: Season,
    @Relation(
        parentColumn = "seasonID",
        entityColumn = "seasonID"
    )
    val seasonLesson: List<Lesson>
)
