package edu.ucne.licorapp.domain.repository

import edu.ucne.licorapp.domain.model.Ordenar
import kotlinx.coroutines.flow.Flow

interface OrdenarRepository {
    fun getOrdenHistory(): Flow<List<Ordenar>>

    suspend fun createOrden(orden: Ordenar): Result<Unit>
    suspend fun refreshOrdenHistory(): Result<Unit>
}