package edu.ucne.licorapp.presentation.checkout

import edu.ucne.licorapp.domain.model.CarritoItem

data class CheckoutUiState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String? = null,
    val items: List<CarritoItem> = emptyList(),
    val total: Double = 0.0
)