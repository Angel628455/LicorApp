package edu.ucne.licorapp.presentation.carrito

sealed class CarritoUiEvent {
    object CargarCarrito : CarritoUiEvent()
    data class EliminarItem(val mercanciaId: Int) : CarritoUiEvent()
    object ProcederAlPago : CarritoUiEvent()
    object LimpiarMensaje : CarritoUiEvent()
    data class IncrementarCantidad(val mercanciaId: Int) : CarritoUiEvent()
    data class DecrementarCantidad(val mercanciaId: Int) : CarritoUiEvent()
}