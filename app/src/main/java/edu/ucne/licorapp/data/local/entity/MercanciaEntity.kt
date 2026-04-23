package edu.ucne.licorapp.data.local.entity
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mercancia")
data class MercanciaEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nombre: String,
    val marca: String?,
    val descripcion: String?,
    val precio: Double,
    val precioOferta: Double?,
    val stock: Int,
    val imagenUrl: String?,
    val categoria: String?,
    val categoriaId: Int,
    val procesador: String?,
    val ram: String?,
    val almacenamiento: String?,
    val calificacion: Double?,
    val galeriaString: String,
    val isSynced: Boolean = true
)