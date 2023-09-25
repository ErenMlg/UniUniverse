package com.softcross.uniuniverse.data.model.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.softcross.uniuniverse.data.model.entities.Exam
import com.softcross.uniuniverse.data.model.entities.Lesson

data class LessonsExam(
    @Embedded val lesson: Lesson,
    @Relation(
        parentColumn = "lessonID",
        entityColumn = "lessonID"
    )
    val lessonExams: List<Exam>
)
