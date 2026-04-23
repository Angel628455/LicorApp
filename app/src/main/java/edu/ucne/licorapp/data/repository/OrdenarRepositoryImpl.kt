package edu.ucne.licorapp.data.repository

import edu.ucne.licorapp.domain.model.Ordenar
import edu.ucne.licorapp.domain.repository.OrdenarRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class OrdenarRepositoryImpl : OrdenarRepository {

    private val ordenes = MutableStateFlow<List<Ordenar>>(emptyList())

    override fun getOrdenHistory(): Flow<List<Ordenar>> = ordenes

    override suspend fun createOrden(orden: Ordenar): Result<Unit> {
        ordenes.value = ordenes.value + orden
        return Result.success(Unit)
    }

    override suspend fun refreshOrdenHistory(): Result<Unit> {
        ordenes.value = emptyList()
        return Result.success(Unit)
    }
}