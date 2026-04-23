package edu.ucne.licorapp.domain.usecase.mercanciausecase

import edu.ucne.licorapp.domain.repository.MercanciaRepository
import javax.inject.Inject

class RefrescarMercanciaUseCase @Inject constructor(
    private val repository: MercanciaRepository
) {
    suspend operator fun invoke(): Result<Unit> {
        return repository.refreshMercancias()
    }
}