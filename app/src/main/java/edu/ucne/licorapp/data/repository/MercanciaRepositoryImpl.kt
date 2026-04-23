package edu.ucne.licorapp.data.repository

import edu.ucne.licorapp.domain.model.Mercancia
import edu.ucne.licorapp.domain.repository.MercanciaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

class MercanciaRepositoryImpl : MercanciaRepository {

    private val lista = MutableStateFlow<List<Mercancia>>(emptyList())

    override fun getMercancias(): Flow<List<Mercancia>> = lista

    override fun buscarMercancia(query: String): Flow<List<Mercancia>> =
        lista.map { it.filter { m -> m.nombre.contains(query, true) } }

    override suspend fun createMercancia(mercancia: Mercancia): Result<Unit> {
        lista.value = lista.value + mercancia
        return Result.success(Unit)
    }

    override suspend fun deleteMercancia(mercanciaId: Int): Result<Unit> {
        lista.value = lista.value.filter { it.id != mercanciaId }
        return Result.success(Unit)
    }

    override suspend fun getMercanciaById(mercanciaId: Int): Result<Mercancia> {
        val item = lista.value.find { it.id == mercanciaId }
            ?: return Result.failure(Exception("No encontrado"))
        return Result.success(item)
    }

    override suspend fun updateMercancia(mercancia: Mercancia): Result<Unit> {
        lista.value = lista.value.map {
            if (it.id == mercancia.id) mercancia else it
        }
        return Result.success(Unit)
    }

    override suspend fun refreshMercancias(): Result<Unit> {
        lista.value = listOf(
            Mercancia(1, "Ron", ""),
            Mercancia(2, "Whisky", "")
        )
        return Result.success(Unit)
    }
}