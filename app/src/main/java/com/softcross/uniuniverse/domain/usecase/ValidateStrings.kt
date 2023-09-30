package com.softcross.uniuniverse.domain.usecase

import com.softcross.uniuniverse.domain.model.ValidationResult

class ValidateStrings {

    fun execute(generalName: String): ValidationResult {
        if (generalName.isEmpty()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Lütfen boş bırakmayınız!"
            )
        }
        return ValidationResult(
            successful = true
        )
    }

}