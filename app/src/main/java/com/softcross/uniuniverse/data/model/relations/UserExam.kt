package com.softcross.uniuniverse.data.model.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.softcross.uniuniverse.data.model.entities.Exam
import com.softcross.uniuniverse.data.model.entities.User

data class UserExam(
    @Embedded val user: User,
    @Relation(
        parentColumn = "userID",
        entityColumn = "userID"
    )
    val userExams: List<Exam>
)
