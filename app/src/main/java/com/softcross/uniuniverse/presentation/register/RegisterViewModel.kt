package com.softcross.uniuniverse.presentation.register

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softcross.uniuniverse.data.model.entities.User
import com.softcross.uniuniverse.data.repository.UserWorksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.softcross.uniuniverse.domain.usecase.ValidateStrings
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private var repo: UserWorksRepository,
) : ViewModel() {

    private val validateString: ValidateStrings = ValidateStrings()
    private var _state = MutableLiveData<RegisterState>()
    val state: LiveData<RegisterState>
        get() = _state

    fun checkInputs(firstName: String, lastName: String, userPhoto: Bitmap) {
        _state.value = RegisterState.Controlling
        val firstNameResult = validateString.execute(firstName)
        val lastNameResult = validateString.execute(lastName)
        _state.value = if (firstNameResult.errorMessage != null) {
            RegisterState.Failed(errorMessage = firstNameResult.errorMessage)
        } else if (lastNameResult.errorMessage != null) {
            RegisterState.Failed(errorMessage = lastNameResult.errorMessage)
        } else {
            repo.addUser(User(0, firstName, lastName, userPhoto))
            RegisterState.Success

        }
    }


    sealed class RegisterState {
        object Controlling : RegisterState()
        object Success : RegisterState()
        data class Failed(val errorMessage: String) : RegisterState()
    }

}