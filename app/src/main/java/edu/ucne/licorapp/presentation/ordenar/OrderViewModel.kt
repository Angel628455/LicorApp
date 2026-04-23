package edu.ucne.licorapp.presentation.ordenar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.licorapp.domain.model.Ordenar
import edu.ucne.licorapp.domain.usecase.ordenarusecase.OrdenarUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val ordenarUseCase: OrdenarUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(OrdenarUiState())
    val state: StateFlow<OrdenarUiState> = _state.asStateFlow()

    init {
        onEvent(OrdenarUiEvent.CargarHistorial)
    }

    fun onEvent(event: OrdenarUiEvent) {
        when (event) {
            is OrdenarUiEvent.CargarHistorial -> cargarPedidos()
            is OrdenarUiEvent.SincronizarConServidor -> {}
            is OrdenarUiEvent.LimpiarError -> {
                _state.update { it.copy(error = null) }
            }
        }
    }

    private fun cargarPedidos() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            val result: Result<List<Ordenar>> = ordenarUseCase.getOrdenarHistory()

            result.onSuccess { lista ->
                _state.update {
                    it.copy(
                        orders = lista,
                        isLoading = false
                    )
                }
            }

            result.onFailure { exception ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        error = exception.message ?: "Error cargando pedidos"
                    )
                }
            }
        }
    }

    fun getOrdenarById(id: Int): Ordenar? {
        return _state.value.orders.find { it.id == id }
    }
}