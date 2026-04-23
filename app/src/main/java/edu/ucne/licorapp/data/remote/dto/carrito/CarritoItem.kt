package edu.ucne.licorapp.data.remote.dto.carrito
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import edu.ucne.licorapp.data.remote.dto.mercancia.MercanciaDto

@JsonClass(generateAdapter = true)
data class CarritoItems(
    @Json(name = "id") val id: Int,
    @Json(name = "usuarioId") val usuarioId: Int,
    @Json(name = " mercanciaId") val mercanciaId: Int,
    @Json(name = "cantidad") val cantidad: Int,
    @Json(name = "mercancia") val mercancia: MercanciaDto?
)