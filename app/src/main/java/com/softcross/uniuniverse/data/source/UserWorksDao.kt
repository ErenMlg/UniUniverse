package com.softcross.uniuniverse.data.source

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.softcross.uniuniverse.data.model.entities.User

@Dao
interface UserWorksDao {

    @Query("Select * From User")
    fun getAllUsers(): LiveData<List<User>>

    @Insert
    fun addUser(user: User) : Long

}