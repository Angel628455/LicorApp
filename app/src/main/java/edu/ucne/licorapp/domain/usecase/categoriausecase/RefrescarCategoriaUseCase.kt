package edu.ucne.licorapp.domain.usecase.categoriausecase

import edu.ucne.licorapp.domain.repository.CategoriaRepository
import javax.inject.Inject

class RefreshCategoriaUseCase @Inject constructor(private val repository: CategoriaRepository
) {
    suspend operator fun invoke(): Result<Unit> = repository.refreshCategories()
}