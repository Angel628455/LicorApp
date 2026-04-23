package edu.ucne.licorapp.presentation.admin

import edu.ucne.licorapp.domain.model.Mercancia

data class AdminUiState(
    val isEditMode: Boolean = false,
    val mercanciaId: Int? = null,
    val mercancia: List<Mercancia> = emptyList(),

    val nombre: String = "",
    val marca: String = "",
    val descripcion: String = "",
    val precio: String = "",
    val stock: String = "",
    val imagenUrl: String = "",
    val categoriaId: String = "",

    val procesador: String = "",
    val ram: String = "",
    val almacenamiento: String = "",

    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val successMessage: String = "",
    val errorMessage: String? = null
)