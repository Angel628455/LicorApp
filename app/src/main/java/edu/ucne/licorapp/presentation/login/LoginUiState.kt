package edu.ucne.licorapp.presentation.login


data class LoginUIState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val isLoginSuccessful: Boolean = false,
    val errorMessage: String? = null
)