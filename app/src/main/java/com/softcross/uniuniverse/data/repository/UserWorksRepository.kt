package com.softcross.uniuniverse.data.repository

import androidx.lifecycle.LiveData
import com.softcross.uniuniverse.data.model.entities.User
import com.softcross.uniuniverse.data.source.UserWorksDao
import javax.inject.Inject

class UserWorksRepository @Inject constructor(private var dao: UserWorksDao) {

    fun getAllUser(): LiveData<List<User>> = dao.getAllUsers()

}