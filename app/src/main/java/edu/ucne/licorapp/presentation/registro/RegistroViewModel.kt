package edu.ucne.licorapp.presentation.registro

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.licorapp.domain.usecase.loginusecase.RegistrarUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistroViewModel @Inject constructor(
    private val registrarUseCase: RegistrarUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegistroUiState())
    val uiState: StateFlow<RegistroUiState> = _uiState.asStateFlow()

    fun onEvent(event: RegistroUiEvent) {
        when (event) {
            is RegistroUiEvent.OnNombreChanged -> _uiState.update { it.copy(nombre = event.nombre) }
            is RegistroUiEvent.OnEmailChanged -> _uiState.update { it.copy(email = event.email) }
            is RegistroUiEvent.OnPasswordChanged -> _uiState.update { it.copy(password = event.password) }
            is RegistroUiEvent.LimpiarError -> _uiState.update { it.copy(errorMessage = null) }
            is RegistroUiEvent.SubmitRegistro -> registrarUsuario()
        }
    }

    private fun registrarUsuario() {
        val currentState = _uiState.value

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }

            val result = registrarUseCase(
                nombre = currentState.nombre,
                email = currentState.email,
                clave = currentState.password
            )

            if (result.isSuccess) {
                _uiState.update { it.copy(isLoading = false, isSuccess = true) }
            } else {
                val error = result.exceptionOrNull()?.message ?: "Error al crear la cuenta"
                _uiState.update { it.copy(isLoading = false, errorMessage = error) }
            }
        }
    }
}