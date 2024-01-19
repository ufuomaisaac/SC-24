package com.example.mothercare.ui.scene.auth.signin

data class LoginState(
    var isLoading: Boolean = false,
    var error: Boolean = false,
    var success: Boolean = false
) {

}
