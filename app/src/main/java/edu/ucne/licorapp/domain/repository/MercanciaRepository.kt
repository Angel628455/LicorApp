package edu.ucne.licorapp.domain.repository

import edu.ucne.licorapp.domain.model.Mercancia
import kotlinx.coroutines.flow.Flow

interface MercanciaRepository {
    fun getMercancias(): Flow<List<Mercancia>>
    fun buscarMercancia(query: String): Flow<List<Mercancia>>
    suspend fun createMercancia(mercancia: Mercancia): Result<Unit>
    suspend fun deleteMercancia(mercanciaId: Int): Result<Unit>
    suspend fun getMercanciaById(mercanciaId: Int): Result<Mercancia>
    suspend fun refreshMercancias(): Result<Unit>

    suspend fun updateMercancia(mercancia: Mercancia): Result<Unit>
}