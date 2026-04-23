package edu.ucne.licorapp.domain.usecase.carritousecase

import edu.ucne.licorapp.domain.repository.CarritoRepository

class SyncCartUseCase(
    private val repository: CarritoRepository
) {
    suspend operator fun invoke(direccion: String, metodoPago: String) =
        repository.procesarCompraFinal(direccion, metodoPago)
}