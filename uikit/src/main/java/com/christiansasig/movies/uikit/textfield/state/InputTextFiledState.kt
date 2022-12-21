package com.christiansasig.movies.uikit.textfield.state

import android.content.Context
import com.christiansasig.movies.core.R
import com.christiansasig.movies.uikit.textfield.util.TextFieldState

class InputTextFiledState(
    context: Context,
    initial: String = ""
) :
    TextFieldState(validator = {
        isTextValid(it.trim())
    }, errorFor = {
        textValidationError(context = context)
    }, showErrors = true,
        initial = initial
    )

private var errorMessage = mutableListOf<Int>()
private var required = true

fun isTextValid(text: String): Boolean {
    var valid = true

    if (text.isEmpty()) {
        valid = false
        required = false
    } else {
        required = true
    }

    getErrorMessage()
    return valid
}

private fun getErrorMessage() {
    errorMessage.clear()
    if (!required) {
        errorMessage.add(R.string.validator_required)
    }
}

private fun textValidationError(context: Context): String {
    var message = ""
    errorMessage.forEach {
        message = message + " " + context.getString(it)
    }

    return message
}