package edu.ucne.licorapp.domain.usecase.loginusecase

import edu.ucne.licorapp.data.local.datastore.SessionDataStore
import edu.ucne.licorapp.domain.repository.AutentificarRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: AutentificarRepository,
    private val sessionDataStore: SessionDataStore
) {
    suspend operator fun invoke(email: String, clave: String): Result<Unit> {
        val result = repository.login(email, clave)

        return if (result.isSuccess) {
            val autentificarResponse = result.getOrNull()

            if (autentificarResponse != null && autentificarResponse.token.isNotEmpty()) {
                sessionDataStore.saveSession(
                    userId = autentificarResponse.usuarioId,
                    name = autentificarResponse.nombre,
                    email = autentificarResponse.email,
                    token = autentificarResponse.token,
                    role = autentificarResponse.rol
                )
                Result.success(Unit)
            } else {
                Result.failure(Exception("El servidor no devolvió un token válido."))
            }
        } else {
            Result.failure(result.exceptionOrNull() ?: Exception("Error desconocido"))
        }
    }
}

data class AutentificarResponse(
    val usuarioId: Int,
    val nombre: String,
    val email: String,
    val token: String,
    val rol: String
)