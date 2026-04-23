package edu.ucne.licorapp.data.remote.dto.login

import com.squareup.moshi.Json

data class AutentificarResponse(
    @Json(name = "usuarioId")
    val usuarioId: Int,

    @Json(name = "nombre")
    val nombre: String,

    @Json(name = "email")
    val email: String,

    @Json(name = "token")
    val token: String,
    val rol: String
)