package edu.ucne.licorapp.domain.usecase.mercanciausecase

import edu.ucne.licorapp.domain.model.Mercancia
import edu.ucne.licorapp.domain.repository.MercanciaRepository
import javax.inject.Inject

class GetMercanciaByIdUseCase @Inject constructor(
    private val repository: MercanciaRepository
) {
    suspend operator fun invoke(mercanciaId: Int): Result<Mercancia> {

        if (mercanciaId <= 0) {
            return Result.failure(Exception("Id inválido"))
        }

        return repository.getMercanciaById(mercanciaId)
    }
}