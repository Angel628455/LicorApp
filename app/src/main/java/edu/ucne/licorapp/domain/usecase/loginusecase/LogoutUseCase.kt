package edu.ucne.licorapp.domain.usecase.loginusecase

import edu.ucne.licorapp.data.local.datastore.SessionDataStore
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val sessionDataStore: SessionDataStore
) {
    suspend operator fun invoke() {
        sessionDataStore.clearSession()
    }
}