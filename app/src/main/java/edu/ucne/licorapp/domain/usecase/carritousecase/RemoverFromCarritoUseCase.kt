package edu.ucne.licorapp.domain.usecase.carritousecase

import edu.ucne.licorapp.domain.repository.CarritoRepository
import javax.inject.Inject

class RemoverFromCarritoUseCase @Inject constructor(
    private val repository: CarritoRepository
) {
    suspend operator fun invoke(mercanciaId: Int): Result<Unit> {
        return repository.removeFromCarrito(mercanciaId)
    }
}