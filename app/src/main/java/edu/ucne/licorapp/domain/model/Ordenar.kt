package edu.ucne.licorapp.domain.model

import edu.ucne.licorapp.domain.model.CarritoItem

data class Ordenar(
    val id: Int? = null,
    val fecha: String,
    val total: Double,
    val estado: String,
    val direccionEnvio: String,
    val metodoPago: String,
    val usuarioId: Int,
    val items: List<CarritoItem>
)