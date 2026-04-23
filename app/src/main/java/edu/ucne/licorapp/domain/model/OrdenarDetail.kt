package edu.ucne.licorapp.domain.model

data class OrdenarDetail(
    val pedidoId: Int,
    val productoId: Int,
    val nombreProducto: String?,
    val cantidad: Int,
    val precioUnitario: Double
)