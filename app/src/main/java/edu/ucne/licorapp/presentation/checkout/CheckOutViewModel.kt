package edu.ucne.licorapp.presentation.checkout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.licorapp.data.local.datastore.SessionDataStore
import edu.ucne.licorapp.domain.model.Ordenar
import edu.ucne.licorapp.domain.usecase.carritousecase.CarritoUseCases
import edu.ucne.licorapp.domain.usecase.ordenarusecase.OrdenarUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CheckoutViewModel @Inject constructor(
    private val carritoUseCases: CarritoUseCases,
    private val ordenarUseCase: OrdenarUseCase,
    private val sessionDataStore: SessionDataStore
) : ViewModel() {

    private val _state = MutableStateFlow(CheckoutUiState())
    val state: StateFlow<CheckoutUiState> = _state.asStateFlow()

    init {
        cargarResumenCarrito()
    }

    private fun cargarResumenCarrito() {
        viewModelScope.launch {
            carritoUseCases.getCart().collect { items ->
                val total = items.sumOf { it.precio * it.cantidad }
                _state.update {
                    it.copy(
                        items = items,
                        total = total
                    )
                }
            }
        }
    }

    fun onEvent(event: CheckoutUiEvent) {
        when (event) {
            is CheckoutUiEvent.ConfirmarPedido -> {
                ejecutarPedido(event.direccion, event.metodoPago)
            }
            is CheckoutUiEvent.LimpiarError -> {
                _state.update { it.copy(error = null) }
            }
        }
    }

    private fun ejecutarPedido(direccion: String, metodoPago: String) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            val userId = sessionDataStore.getUserId().first()
            val items = _state.value.items
            val total = _state.value.total

            if (userId == null) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        error = "Sesión no válida."
                    )
                }
                return@launch
            }

            if (items.isEmpty()) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        error = "El carrito está vacío."
                    )
                }
                return@launch
            }

            val orden = Ordenar(
                id = null,
                fecha = System.currentTimeMillis().toString(),
                total = total,
                estado = "Pendiente",
                direccionEnvio = direccion,
                metodoPago = metodoPago,
                usuarioId = userId,
                items = items
            )

            val result = ordenarUseCase.createOrden(orden)

            if (result.isSuccess) {

                carritoUseCases.clearCarrito()

                _state.update {
                    it.copy(
                        isLoading = false,
                        isSuccess = true
                    )
                }

            } else {

                _state.update {
                    it.copy(
                        isLoading = false,
                        error = result.exceptionOrNull()?.message
                            ?: "Error al procesar el pedido"
                    )
                }
            }
        }
    }
}