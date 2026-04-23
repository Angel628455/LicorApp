package edu.ucne.licorapp.data.remote.dto.registrar
import com.squareup.moshi.Json

data class RegistrarRequest(
    val nombre: String,
    val email: String,
    @Json(name = "clave")
    val clave: String
)