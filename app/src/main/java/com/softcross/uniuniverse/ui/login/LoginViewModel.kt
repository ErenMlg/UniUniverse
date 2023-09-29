package com.softcross.uniuniverse.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softcross.uniuniverse.data.model.entities.User
import com.softcross.uniuniverse.data.repository.UserWorksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private var repo: UserWorksRepository) : ViewModel() {

    fun getAllUser(): LiveData<List<User>> = repo.getAllUser()


}