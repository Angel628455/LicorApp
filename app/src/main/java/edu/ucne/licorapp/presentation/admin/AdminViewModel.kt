package edu.ucne.licorapp.presentation.admin

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.licorapp.domain.model.Mercancia
import edu.ucne.licorapp.domain.repository.MercanciaRepository
import edu.ucne.licorapp.domain.usecase.mercanciausecase.CreateMercanciaUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdminViewModel @Inject constructor(
    private val createMercanciaUseCase: CreateMercanciaUseCase,
    private val repository: MercanciaRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(AdminUiState())
    val uiState: StateFlow<AdminUiState> = _uiState.asStateFlow()

    init {
        // ✔ LISTAR
        viewModelScope.launch {
            repository.getMercancias().collect { lista: List<Mercancia> ->
                _uiState.update { it.copy(mercancia = lista) }
            }
        }

        val mercanciaId = savedStateHandle.get<String>("mercanciaId")?.toIntOrNull()
        if (mercanciaId != null && mercanciaId != -1) {
            cargarDatosDeMercancia(mercanciaId)
        }
    }

    private fun cargarDatosDeMercancia(id: Int) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            val result = repository.getMercanciaById(id)

            result.fold(
                onSuccess = { mercancia ->
                    onEvent(AdminUiEvent.CargarMercanciaParaEditar(mercancia))
                },
                onFailure = {
                    _uiState.update {
                        it.copy(isLoading = false, errorMessage = "Error cargando datos")
                    }
                }
            )
        }
    }

    fun onEvent(event: AdminUiEvent) {
        when (event) {

            is AdminUiEvent.CargarMercanciaParaEditar -> {
                _uiState.update {
                    it.copy(
                        isEditMode = true,
                        mercanciaId = event.mercancia.id,
                        nombre = event.mercancia.nombre,
                        marca = event.mercancia.marca ?: "",
                        descripcion = event.mercancia.descripcion ?: "",
                        precio = event.mercancia.precio.toString(),
                        stock = event.mercancia.stock.toString(),
                        imagenUrl = event.mercancia.imagenUrl ?: "",
                        categoriaId = event.mercancia.categoriaId.toString(),
                        procesador = event.mercancia.procesador ?: "",
                        ram = event.mercancia.ram ?: "",
                        almacenamiento = event.mercancia.almacenamiento ?: "",
                        isLoading = false
                    )
                }
            }

            is AdminUiEvent.OnEditClicked -> {
                cargarDatosDeMercancia(event.id)
            }

            is AdminUiEvent.OnDeleteClicked -> {
                viewModelScope.launch {
                    _uiState.update { it.copy(isLoading = true) }

                    val result = repository.deleteMercancia(event.id)

                    if (result.isSuccess) {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                isSuccess = true,
                                successMessage = "¡Eliminado correctamente!"
                            )
                        }
                    } else {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                errorMessage = "Error al eliminar"
                            )
                        }
                    }
                }
            }

            is AdminUiEvent.OnNombreChanged -> _uiState.update { it.copy(nombre = event.nombre) }
            is AdminUiEvent.OnMarcaChanged -> _uiState.update { it.copy(marca = event.marca) }
            is AdminUiEvent.OnDescripcionChanged -> _uiState.update { it.copy(descripcion = event.descripcion) }
            is AdminUiEvent.OnPrecioChanged -> _uiState.update { it.copy(precio = event.precio) }
            is AdminUiEvent.OnStockChanged -> _uiState.update { it.copy(stock = event.stock) }
            is AdminUiEvent.OnImagenUrlChanged -> _uiState.update { it.copy(imagenUrl = event.url) }
            is AdminUiEvent.OnCategoriaIdChanged -> _uiState.update { it.copy(categoriaId = event.id) }

            is AdminUiEvent.SubmitMercancia -> guardarMercancia()

            is AdminUiEvent.LimpiarError -> _uiState.update { it.copy(errorMessage = null) }
            is AdminUiEvent.ResetSuccess -> _uiState.update { AdminUiState(mercancia = it.mercancia) }
        }
    }

    private fun guardarMercancia() {
        val state = _uiState.value

        val precio = state.precio.toDoubleOrNull()
        val stock = state.stock.toIntOrNull()
        val categoriaId = state.categoriaId.toIntOrNull()

        if (state.nombre.isBlank() || precio == null || stock == null || categoriaId == null) {
            _uiState.update {
                it.copy(errorMessage = "Campos inválidos")
            }
            return
        }

        val mercancia = Mercancia(
            id = state.mercanciaId ?: 0,
            nombre = state.nombre,
            marca = state.marca,
            descripcion = state.descripcion,
            precio = precio,
            precioOferta = null,
            stock = stock,
            imagenUrl = state.imagenUrl,
            categoria = "",
            categoriaId = categoriaId,
            procesador = state.procesador,
            ram = state.ram,
            almacenamiento = state.almacenamiento,
            calificacion = 5.0,
            galeriaString = ""
        )

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            val result = if (state.isEditMode) {
                repository.updateMercancia(mercancia)
            } else {
                createMercanciaUseCase(mercancia)
            }

            if (result.isSuccess) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        isSuccess = true,
                        successMessage = "Guardado correctamente"
                    )
                }
            } else {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = result.exceptionOrNull()?.message
                    )
                }
            }
        }
    }
}