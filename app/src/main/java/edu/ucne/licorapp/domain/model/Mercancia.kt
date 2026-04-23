package edu.ucne.licorapp.domain.model

data class Mercancia(
    val id: Int = 0,
    val nombre: String = "",
    val marca: String? = null,
    val descripcion: String? = null,
    val precio: Double = 0.0,
    val precioOferta: Double? = null,
    val stock: Int = 0,
    val imagenUrl: String? = null,
    val categoria: String? = null,
    val categoriaId: Int = 0,
    val procesador: String? = null,
    val ram: String? = null,
    val almacenamiento: String? = null,
    val calificacion: Double? = null,
    val galeriaString: String = "",
    val isSynced: Boolean = true
)