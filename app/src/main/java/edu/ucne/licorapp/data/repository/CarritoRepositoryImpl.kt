package edu.ucne.licorapp.data.repository

import edu.ucne.licorapp.domain.model.CarritoItem
import edu.ucne.licorapp.domain.repository.CarritoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class CarritoRepositoryImpl : CarritoRepository {

    private val carrito = MutableStateFlow<List<CarritoItem>>(emptyList())

    override suspend fun addToCarritoLocal(item: CarritoItem): Result<Unit> {
        carrito.value = carrito.value + item
        return Result.success(Unit)
    }

    override suspend fun decrementQuantity(mercanciaId: Int) {
        carrito.value = carrito.value.map {
            if (it.mercanciaId == mercanciaId && it.cantidad > 1)
                it.copy(cantidad = it.cantidad - 1)
            else it
        }
    }

    override fun getCarrito(): Flow<List<CarritoItem>> = carrito

    override suspend fun removeFromCarrito(mercanciaId: Int): Result<Unit> {
        carrito.value = carrito.value.filter { it.mercanciaId != mercanciaId }
        return Result.success(Unit)
    }

    override suspend fun procesarCompraFinal(
        direccion: String,
        metodoPago: String
    ): Result<Unit> {
        carrito.value = emptyList()
        return Result.success(Unit)
    }
}