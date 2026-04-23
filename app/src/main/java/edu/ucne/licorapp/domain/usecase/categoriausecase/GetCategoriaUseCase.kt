package edu.ucne.licorapp.domain.usecase.categoriausecase

import edu.ucne.licorapp.domain.model.Categoria
import edu.ucne.licorapp.domain.repository.CategoriaRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoriaUseCase @Inject constructor(private val repository: CategoriaRepository
) {
    operator fun invoke(): Flow<List<Categoria>> = repository.getCategorias()
}