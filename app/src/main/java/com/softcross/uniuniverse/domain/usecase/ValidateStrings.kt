package com.softcross.uniuniverse.domain.usecase

import android.app.Application
import android.content.res.Resources
import android.media.Ringtone
import com.softcross.uniuniverse.R
import com.softcross.uniuniverse.domain.model.ValidationResult
import com.softcross.uniuniverse.infrastructure.StringResourceProvider
import javax.inject.Inject

class ValidateStrings @Inject constructor(stringResourceProvider: StringResourceProvider) {

    private val errorMessage = stringResourceProvider.invoke(R.string.strPleaseFillBlanks)
    fun execute(generalName: String): ValidationResult {
        if (generalName.isEmpty()) {
            return ValidationResult(
                successful = false,
                errorMessage = errorMessage
            )
        }
        return ValidationResult(
            successful = true
        )
    }

}