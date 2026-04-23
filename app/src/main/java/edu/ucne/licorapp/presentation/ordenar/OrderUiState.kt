package edu.ucne.licorapp.presentation.ordenar

import edu.ucne.licorapp.domain.model.Ordenar


data class OrdenarUiState(
    val orders: List<Ordenar> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)