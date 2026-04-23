package edu.ucne.licorapp.data.repository

import edu.ucne.licorapp.domain.repository.AutentificarRepository
import edu.ucne.licorapp.domain.usecase.loginusecase.AutentificarResponse

class AutentificarRepositoryImpl : AutentificarRepository {

    override suspend fun registrar(
        nombre: String,
        email: String,
        clave: String
    ): Result<Unit> {
        return Result.success(Unit)
    }

    override suspend fun login(
        email: String,
        clave: String
    ): Result<AutentificarResponse> {
        return Result.success(
            AutentificarResponse(
                usuarioId = 1,
                nombre = "Usuario",
                email = email,
                token = "token123",
                rol = "User"
            )
        )
    }
}