package com.softcross.uniuniverse.ui.register

import androidx.lifecycle.ViewModel
import com.softcross.uniuniverse.data.repository.UserWorksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private var repo: UserWorksRepository) : ViewModel() {
}