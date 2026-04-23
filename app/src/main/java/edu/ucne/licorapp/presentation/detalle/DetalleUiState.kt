package edu.ucne.licorapp.presentation.detalle

import edu.ucne.licorapp.domain.model.Mercancia

data class DetalleUiState(
    val mercancia: Mercancia? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val cartMessage: String? = null,
    val isFavorite: Boolean = false
)