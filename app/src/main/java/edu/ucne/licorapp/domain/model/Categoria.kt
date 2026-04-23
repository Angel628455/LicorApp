package edu.ucne.licorapp.domain.model

data class Categoria(
    val id: Int,
    val nombre: String,
    val imagenIconoUrl: String? = null
)