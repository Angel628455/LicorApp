package edu.ucne.licorapp.presentation.admin

import edu.ucne.licorapp.domain.model.Mercancia

sealed class AdminUiEvent {
    data class CargarMercanciaParaEditar(val mercancia: Mercancia) : AdminUiEvent()

    data class OnNombreChanged(val nombre: String) : AdminUiEvent()
    data class OnEditClicked(val id: Int) : AdminUiEvent()
    data class OnDeleteClicked(val id: Int) : AdminUiEvent()
    data class OnMarcaChanged(val marca: String) : AdminUiEvent()
    data class OnDescripcionChanged(val descripcion: String) : AdminUiEvent()
    data class OnPrecioChanged(val precio: String) : AdminUiEvent()
    data class OnStockChanged(val stock: String) : AdminUiEvent()
    data class OnImagenUrlChanged(val url: String) : AdminUiEvent()
    data class OnCategoriaIdChanged(val id: String) : AdminUiEvent()
    object SubmitMercancia : AdminUiEvent()
    object LimpiarError : AdminUiEvent()
    object ResetSuccess : AdminUiEvent()
}