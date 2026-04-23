package edu.ucne.licorapp.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.licorapp.domain.repository.AutentificarRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val autentificarRepository: AutentificarRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUIState())
    val uiState: StateFlow<LoginUIState> = _uiState.asStateFlow()

    fun onEvent(event: LoginUIEvent) {
        when (event) {
            is LoginUIEvent.OnEmailChanged -> {
                _uiState.update { it.copy(email = event.email) }
            }

            is LoginUIEvent.OnPasswordChanged -> {
                _uiState.update { it.copy(password = event.password) }
            }

            is LoginUIEvent.SubmitLogin -> {
                realizarLogin()
            }

            is LoginUIEvent.LimpiarError -> {
                _uiState.update { it.copy(errorMessage = null) }
            }
        }
    }

    private fun realizarLogin() {
        val email = uiState.value.email
        val password = uiState.value.password

        if (_uiState.value.isLoading) return

        if (email.isBlank() || password.isBlank()) {
            _uiState.update {
                it.copy(errorMessage = "Por favor completa todos los campos.")
            }
            return
        }

        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true, errorMessage = null, isLoginSuccessful = false)
            }

            val result = autentificarRepository.login(email, password)

            result.fold(
                onSuccess = { response ->
                    if (response.token.isNotEmpty()) {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                isLoginSuccessful = true,
                                errorMessage = null
                            )
                        }
                    } else {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                errorMessage = "Token inválido."
                            )
                        }
                    }
                },
                onFailure = { error ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = error.message ?: "Error desconocido"
                        )
                    }
                }
            )
        }
    }
}