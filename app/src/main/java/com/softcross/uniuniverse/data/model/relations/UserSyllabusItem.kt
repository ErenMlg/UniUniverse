package com.softcross.uniuniverse.data.model.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.softcross.uniuniverse.data.model.entities.SyllabusItem
import com.softcross.uniuniverse.data.model.entities.User

data class UserSyllabusItem(
    @Embedded val user: User,
    @Relation(
        parentColumn = "userID",
        entityColumn = "userID"
    )
    val userClasses:List<SyllabusItem>
)
