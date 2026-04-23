package edu.ucne.licorapp.domain.usecase.ordenarusecase

import edu.ucne.licorapp.domain.model.Ordenar
import edu.ucne.licorapp.domain.repository.OrdenarRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetOrdenarHistoryUseCase @Inject constructor(
    private val repository: OrdenarRepository
) {
    operator fun invoke(): Flow<List<Ordenar>> {
        return repository.getOrdenHistory()
    }
}