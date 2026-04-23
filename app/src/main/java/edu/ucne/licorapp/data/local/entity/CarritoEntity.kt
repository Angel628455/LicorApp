package edu.ucne.licorapp.data.local.entity


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "carritoitems")
data class CarritoEntity(
    @PrimaryKey val mercanciaId: Int,
    val nombre: String,
    val precio: Double,
    val imagenUrl: String,
    val cantidad: Int,
    val usuarioId: Int,
    val isSynced: Boolean = false
)