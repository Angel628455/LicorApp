package edu.ucne.licorapp.domain.usecase.mercanciausecase

import edu.ucne.licorapp.domain.model.Mercancia
import edu.ucne.licorapp.domain.repository.MercanciaRepository
import javax.inject.Inject

class CreateMercanciaUseCase @Inject constructor(
    private val repository: MercanciaRepository
) {
    suspend operator fun invoke(mercancia: Mercancia): Result<Unit> {
        if (mercancia.nombre.isBlank()) {
            return Result.failure(Exception("El nombre no puede estar vacío"))
        }

        if (mercancia.precio < 0) {
            return Result.failure(Exception("El precio no puede ser negativo"))
        }

        return repository.createMercancia(mercancia)
    }
}