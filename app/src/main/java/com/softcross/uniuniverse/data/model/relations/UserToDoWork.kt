package com.softcross.uniuniverse.data.model.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.softcross.uniuniverse.data.model.entities.ToDoWork
import com.softcross.uniuniverse.data.model.entities.User

data class UserToDoWork(
    @Embedded val user: User,
    @Relation(
        parentColumn = "userID",
        entityColumn = "userID"
    )
    val userWorks: List<ToDoWork>
)
