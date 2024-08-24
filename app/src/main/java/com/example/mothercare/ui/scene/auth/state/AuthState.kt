package com.example.mothercare.ui.scene.auth.state

import androidx.compose.runtime.State

data class AuthState(
    var signedIn: Boolean = false,
    var signedUp: Boolean = false
)

sealed class AuthUiState {

    /**
     * Empty state when the screen is first shown
     */
     data object Initial: AuthUiState()

    /**
     * Still loading
     */
    data object Loading: AuthUiState()

    /**
     * success
     */
    data object Success: AuthUiState()

    /**
     * There was an error
     */
    data class Error(
        val errorMessage: String
    ): AuthUiState()

}
