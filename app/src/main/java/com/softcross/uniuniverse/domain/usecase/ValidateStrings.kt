package com.softcross.uniuniverse.domain.usecase

import android.content.res.Resources
import android.media.Ringtone
import com.softcross.uniuniverse.R
import com.softcross.uniuniverse.domain.model.ValidationResult

class ValidateStrings {

    fun execute(generalName: String): ValidationResult {
        if (generalName.isEmpty()) {
            return ValidationResult(
                successful = false,
                errorMessage = Resources.getSystem().getString(R.string.strPleaseFillBlanks)
            )
        }
        return ValidationResult(
            successful = true
        )
    }

}