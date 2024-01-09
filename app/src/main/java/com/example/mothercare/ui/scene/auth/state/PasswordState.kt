package com.example.mothercare.ui.scene.auth.state

class PasswordState:
    TextFieldState(validator = ::isPasswordValid, errorFor = ::passwordValidatorError)


class ConfirmPasswordState(private val passwordState: PasswordState): TextFieldState() {

    override val isValid: Boolean
        get() = passwordAndConfirmationValidation(passwordState.text, text)


    override fun getError(): String? {
        return if (showErrors()) {
            return passwordConfirmationError()
        } else {
            null
        }
    }
}

fun passwordAndConfirmationValidation(password: String, confirmPassword: String) : Boolean {
     return isPasswordValid(password) && password == confirmPassword
 }
private fun isPasswordValid(password: String): Boolean {
    return password.length > 7
}

private fun passwordValidatorError(password: String): String {
    return "Password is less than 8 "
}

private fun passwordConfirmationError(): String {
    return "Password don't match"
}