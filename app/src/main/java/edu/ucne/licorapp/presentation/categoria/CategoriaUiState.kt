package edu.ucne.licorapp.presentation.categoria

import edu.ucne.licorapp.domain.model.Categoria

data class CategoriaUiState(
    val categories: List<Categoria> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)