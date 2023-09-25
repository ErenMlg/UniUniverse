package com.softcross.uniuniverse.data.model.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.softcross.uniuniverse.data.model.entities.Season
import com.softcross.uniuniverse.data.model.entities.User

data class UserSeason(
    @Embedded val user: User,
    @Relation(
        parentColumn = "userID",
        entityColumn = "userID"
    )
    val userSeasons:List<Season>
)
