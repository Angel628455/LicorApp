package edu.ucne.licorapp.domain.usecase.carritousecase

import edu.ucne.licorapp.domain.model.CarritoItem
import edu.ucne.licorapp.domain.repository.CarritoRepository
import javax.inject.Inject

class AddToCarritoUseCase @Inject constructor(
    private val repository: CarritoRepository
) {
    suspend operator fun invoke(item: CarritoItem): Result<Unit> {
        if (item.cantidad <= 0) {
            return Result.failure(Exception("La cantidad a comprar debe ser mayor a 0"))
        }
        if (item.precio < 0) {
            return Result.failure(Exception("El precio no puede ser negativo"))
        }

        return repository.addToCarritoLocal(item)
    }
}