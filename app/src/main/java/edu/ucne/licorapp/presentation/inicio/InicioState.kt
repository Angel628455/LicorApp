package edu.ucne.licorapp.presentation.inicio

import edu.ucne.licorapp.domain.model.Mercancia

data class InicioState(
    val isLoading: Boolean = false,
    val mercancia: List<Mercancia> = emptyList(),
    val searchQuery: String = "",
    val error: String? = null,
    val isAdmin: Boolean = false,
    val activeCategory: String? = null
)