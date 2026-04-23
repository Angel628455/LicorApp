package edu.ucne.licorapp.data.local.entity
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuario_profile")
data class UsuarioEntity(
    @PrimaryKey val id: Int,
    val nombreCompleto: String?,
    val email: String,
    val rol: String?,
    val fechaRegistro: String?
)