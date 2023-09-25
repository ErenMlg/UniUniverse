package com.softcross.uniuniverse.data.model.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.softcross.uniuniverse.data.model.entities.Lesson
import com.softcross.uniuniverse.data.model.entities.User

data class UserLesson(
    @Embedded val user: User,
    @Relation(
        parentColumn = "userID",
        entityColumn = "userID"
    )
    val userLessons: List<Lesson>
)
