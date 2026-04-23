package edu.ucne.licorapp.domain.repository

import edu.ucne.licorapp.domain.usecase.loginusecase.AutentificarResponse

interface AutentificarRepository {

    suspend fun registrar(
        nombre: String,
        email: String,
        clave: String
    ): Result<Unit>

    suspend fun login(
        email: String,
        clave: String
    ): Result<AutentificarResponse>
}