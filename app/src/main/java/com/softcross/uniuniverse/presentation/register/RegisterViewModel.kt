package com.softcross.uniuniverse.presentation.register

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.softcross.uniuniverse.data.model.entities.User
import com.softcross.uniuniverse.data.repository.UserWorksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.softcross.uniuniverse.domain.usecase.ValidateStrings
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repo: UserWorksRepository,
    private val validateStrings: ValidateStrings
) : ViewModel() {

    private var _state = Channel<RegisterState>()
    val state: Flow<RegisterState>
        get() = _state.receiveAsFlow()

    fun checkInputs(firstName: String, lastName: String, userPhoto: Bitmap) =
        viewModelScope.launch(Dispatchers.IO) {
            _state.send(RegisterState.Controlling)
            val firstNameResult = validateStrings.execute(firstName)
            val lastNameResult = validateStrings.execute(lastName)
            if (firstNameResult.errorMessage != null) {
                _state.send(RegisterState.Failed(errorMessage = firstNameResult.errorMessage))
            } else if (lastNameResult.errorMessage != null) {
                _state.send(RegisterState.Failed(errorMessage = lastNameResult.errorMessage))
            } else {
                repo.addUser(User(0, firstName, lastName, userPhoto))
                _state.send(RegisterState.Success)
            }
        }

    sealed class RegisterState {
        data object Controlling : RegisterState()
        data object Success : RegisterState()
        data class Failed(val errorMessage: String) : RegisterState()
    }
}