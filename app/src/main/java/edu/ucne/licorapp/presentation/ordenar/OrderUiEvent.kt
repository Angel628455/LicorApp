package edu.ucne.licorapp.presentation.ordenar


sealed class OrdenarUiEvent {
    object CargarHistorial : OrdenarUiEvent()
    object SincronizarConServidor : OrdenarUiEvent()
    object LimpiarError : OrdenarUiEvent()
}