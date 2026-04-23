package edu.ucne.licorapp.presentation.inicio

sealed class InicioUiEvent {
    object CargarMercanciaIniciales : InicioUiEvent()
    object RefrescarPantalla : InicioUiEvent()
    data class Buscar(val query: String) : InicioUiEvent()
    object LimpiarFiltro : InicioUiEvent()
    data class MercanciaSeleccionado(val id: Int) : InicioUiEvent()
}