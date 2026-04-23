package edu.ucne.licorapp.domain.usecase.ordenarusecase

import edu.ucne.licorapp.domain.repository.OrdenarRepository
import javax.inject.Inject

class RefrescarOrdenarHistoryUseCase @Inject constructor(
    private val repository: OrdenarRepository
) {
    suspend operator fun invoke(): Result<Unit> {
        return repository.refreshOrdenHistory()
    }
}