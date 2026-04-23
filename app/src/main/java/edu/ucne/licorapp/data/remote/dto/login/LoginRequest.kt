package edu.ucne.licorapp.data.remote.dto.login

import com.squareup.moshi.Json

data class LoginRequest(
    val email: String,
    @Json(name = "clave")
    val clave: String
)