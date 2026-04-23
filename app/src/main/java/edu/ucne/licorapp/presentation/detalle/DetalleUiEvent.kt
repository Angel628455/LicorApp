package edu.ucne.licorapp.presentation.detalle
sealed class DetalleUiEvent {
    data class CargarMercancia(val id: Int) : DetalleUiEvent()
    object AgregarAlCarrito : DetalleUiEvent()
    object LimpiarMensajeCarrito : DetalleUiEvent()
    object ToggleFavorito : DetalleUiEvent()
}