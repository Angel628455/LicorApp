package edu.ucne.licorapp.domain.usecase.loginusecase

data class AutentificarUseCase(
    val login: LoginUseCase,
    val register: RegistrarUseCase,
    val logout: LogoutUseCase
)