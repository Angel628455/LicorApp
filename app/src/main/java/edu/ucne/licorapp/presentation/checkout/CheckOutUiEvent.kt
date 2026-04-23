package edu.ucne.licorapp.presentation.checkout

sealed class CheckoutUiEvent {
    data class ConfirmarPedido(
        val direccion: String,
        val metodoPago: String
    ) : CheckoutUiEvent()

    object LimpiarError : CheckoutUiEvent()
}