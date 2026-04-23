package edu.ucne.licorapp.data.repository

import edu.ucne.licorapp.domain.model.Categoria
import edu.ucne.licorapp.domain.repository.CategoriaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class CategoriaRepositoryImpl : CategoriaRepository {

    private val categorias = MutableStateFlow<List<Categoria>>(emptyList())

    override fun getCategorias(): Flow<List<Categoria>> = categorias

    override suspend fun refreshCategories(): Result<Unit> {
        categorias.value = listOf(
            Categoria(1, "Ron"),
            Categoria(2, "Whisky")
        )
        return Result.success(Unit)
    }
}