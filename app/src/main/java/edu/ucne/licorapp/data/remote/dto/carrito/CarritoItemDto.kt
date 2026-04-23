package edu.ucne.licorapp.data.remote.dto.carrito
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CarritoItemDto(
    @Json(name = "usuarioId") val usuarioId: Int,
    @Json(name = "mercanciaId") val mercanciaId: Int,
    @Json(name = "cantidad") val cantidad: Int
)