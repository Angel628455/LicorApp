package edu.ucne.licorapp.presentation.carrito

import edu.ucne.licorapp.domain.model.CarritoItem


data class CarritoUiState(
    val items: List<CarritoItem> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val total: Double = 0.0
)