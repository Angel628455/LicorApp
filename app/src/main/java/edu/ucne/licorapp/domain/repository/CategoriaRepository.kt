package edu.ucne.licorapp.domain.repository

import edu.ucne.licorapp.domain.model.Categoria
import kotlinx.coroutines.flow.Flow

interface CategoriaRepository {
    fun getCategorias(): Flow<List<Categoria>>
    suspend fun refreshCategories(): Result<Unit>
}