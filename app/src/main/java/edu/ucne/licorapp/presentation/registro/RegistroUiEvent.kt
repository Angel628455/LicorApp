package edu.ucne.licorapp.presentation.registro



sealed class RegistroUiEvent {
    data class OnNombreChanged(val nombre: String) : RegistroUiEvent()
    data class OnEmailChanged(val email: String) : RegistroUiEvent()
    data class OnPasswordChanged(val password: String) : RegistroUiEvent()
    object SubmitRegistro : RegistroUiEvent()
    object LimpiarError : RegistroUiEvent()
}