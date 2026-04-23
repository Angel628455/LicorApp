package edu.ucne.licorapp.domain.usecase.loginusecase

import edu.ucne.licorapp.domain.repository.AutentificarRepository
import javax.inject.Inject

class RegistrarUseCase @Inject constructor(
    private val repository: AutentificarRepository
) {
    suspend operator fun invoke(nombre: String, email: String, clave: String): Result<Unit> {
        if (nombre.isBlank() || email.isBlank() || clave.isBlank()) {
            return Result.failure(Exception("Todos los campos son obligatorios"))
        }
        if (clave.length < 6) {
            return Result.failure(Exception("La contraseña debe tener al menos 6 caracteres"))
        }
        if (!email.contains("@")) {
            return Result.failure(Exception("Correo electrónico inválido"))
        }

        return repository.registrar(nombre, email, clave)
    }
}