package edu.ucne.licorapp.domain.model

data class CarritoItem(
    val mercanciaId: Int,
    val nombre: String,
    val precio: Double,
    val imagenUrl: String,
    val cantidad: Int
)