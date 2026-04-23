package edu.ucne.licorapp.domain.usecase.mercanciausecase

import edu.ucne.licorapp.domain.model.Mercancia
import edu.ucne.licorapp.domain.repository.MercanciaRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMercanciaUseCase @Inject constructor(
    private val repository: MercanciaRepository
) {
    operator fun invoke(): Flow<List<Mercancia>> {
        return repository.getMercancias()
    }
}