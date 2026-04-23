package edu.ucne.licorapp.domain.usecase.mercanciausecase

import edu.ucne.licorapp.domain.repository.MercanciaRepository
import javax.inject.Inject

class DeleteMercanciaUseCase @Inject constructor(
    private val repository: MercanciaRepository
) {
    suspend operator fun invoke(mercanciaId: Int): Result<Unit> {

        if (mercanciaId <= 0) {
            return Result.failure(Exception("Id inválido"))
        }

        return repository.deleteMercancia(mercanciaId)
    }
}