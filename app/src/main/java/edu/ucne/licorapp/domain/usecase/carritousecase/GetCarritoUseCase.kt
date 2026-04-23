package edu.ucne.licorapp.domain.usecase.carritousecase

import edu.ucne.licorapp.domain.model.CarritoItem
import edu.ucne.licorapp.domain.repository.CarritoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCarritoUseCase @Inject constructor(
    private val repository: CarritoRepository
) {
    operator fun invoke(): Flow<List<CarritoItem>> {
        return repository.getCarrito()
    }
}