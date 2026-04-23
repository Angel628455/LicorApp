package edu.ucne.licorapp.domain.usecase.mercanciausecase

import javax.inject.Inject

data class MercanciaUseCases @Inject constructor(
    val getMercancias: GetMercanciaUseCase,
    val getMercanciaById: GetMercanciaByIdUseCase,
    val createMercancia: CreateMercanciaUseCase,
    val updateMercancia: UpdateMercanciaUseCase,
    val deleteMercancia: DeleteMercanciaUseCase,
    val buscarMercancia: BuscarMercanciaUseCase,
    val refrescarMercancia: RefrescarMercanciaUseCase
)