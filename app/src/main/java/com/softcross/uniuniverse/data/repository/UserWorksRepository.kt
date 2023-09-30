package com.softcross.uniuniverse.data.repository

import androidx.lifecycle.LiveData
import com.softcross.uniuniverse.data.model.entities.User
import com.softcross.uniuniverse.data.source.UserWorksDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserWorksRepository @Inject constructor(private var dao: UserWorksDao) {

    fun getAllUser(): LiveData<List<User>> = dao.getAllUsers()

    fun addUser(user: User) = CoroutineScope(Dispatchers.IO).launch{
        dao.addUser(user)
    }

}