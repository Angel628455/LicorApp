package edu.ucne.licorapp.domain.usecase.ordenarusecase

import edu.ucne.licorapp.domain.model.Ordenar
import edu.ucne.licorapp.domain.repository.OrdenarRepository
import javax.inject.Inject

class CreateOrdenarUseCase @Inject constructor(
    private val repository: OrdenarRepository
) {
    suspend operator fun invoke(orden: Ordenar): Result<Unit> {

        if (orden.items.isEmpty()) {
            return Result.failure(Exception("La orden no puede estar vacía"))
        }

        if (orden.total <= 0) {
            return Result.failure(Exception("Total inválido"))
        }

        return repository.createOrden(orden)
    }
}