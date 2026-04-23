package edu.ucne.licorapp.data.remote.dto.pedido

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import edu.ucne.licorapp.data.remote.dto.mercancia.MercanciaDto

@JsonClass(generateAdapter = true)
data class PedidoDto(
    @Json(name = "id") val id: Int,
    @Json(name = "fecha") val fecha: String?,
    @Json(name = "total") val total: Double,
    @Json(name = "estado") val estado: String?,
    @Json(name = "direccionEnvio") val direccionEnvio: String?,
    @Json(name = "metodoPago") val metodoPago: String?,
    @Json(name = "usuarioId") val usuarioId: Int,
    @Json(name = "detalles") val detalles: List<DetallePedidoDTO>?
)

@JsonClass(generateAdapter = true)
data class DetallePedidoDTO(
    @Json(name = "id") val id: Int,
    @Json(name = "pedidoId") val pedidoId: Int,
    @Json(name = "mercanciaId") val mercanciaId: Int,
    @Json(name = "mercancia") val mercancia: MercanciaDto?,
    @Json(name = "cantidad") val cantidad: Int,
    @Json(name = "precioUnitario") val precioUnitario: Double
)