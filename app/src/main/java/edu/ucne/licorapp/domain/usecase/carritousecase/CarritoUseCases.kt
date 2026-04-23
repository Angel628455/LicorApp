package edu.ucne.licorapp.domain.usecase.carritousecase

import edu.ucne.licorapp.domain.model.CarritoItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CarritoUseCases @Inject constructor() {

    fun getCart(): Flow<List<CarritoItem>> = flow {
        emit(emptyList())
    }

    suspend fun clearCarrito() {
    }
}