package edu.ucne.licorapp.domain.repository

import edu.ucne.licorapp.domain.model.CarritoItem
import kotlinx.coroutines.flow.Flow

interface CarritoRepository {
    suspend fun addToCarritoLocal(item: CarritoItem): Result<Unit>
    suspend fun decrementQuantity(mercanciaId: Int)
    fun getCarrito(): Flow<List<CarritoItem>>
    suspend fun removeFromCarrito(productoId: Int): Result<Unit>

    suspend fun procesarCompraFinal(
        direccion: String,
        metodoPago: String
    ): Result<Unit>
}