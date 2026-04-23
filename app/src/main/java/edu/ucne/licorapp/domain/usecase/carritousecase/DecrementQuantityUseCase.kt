package edu.ucne.licorapp.domain.usecase.carritousecase

import edu.ucne.licorapp.domain.repository.CarritoRepository

class DecrementQuantityUseCase(
    private val repository: CarritoRepository
) {
    suspend operator fun invoke(mercanciaId: Int) {
        repository.decrementQuantity(mercanciaId)
    }
}