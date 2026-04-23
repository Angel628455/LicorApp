package edu.ucne.licorapp.presentation.categoria

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.licorapp.domain.usecase.categoriausecase.CategoriaUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriaViewModel @Inject constructor(
    private val categoriaUseCases: CategoriaUseCases
) : ViewModel() {

    private val _state = MutableStateFlow(CategoriaUiState())
    val state: StateFlow<CategoriaUiState> = _state.asStateFlow()

    init {
        onEvent(CategoriaUiEvent.LoadCategories)
        onEvent(CategoriaUiEvent.RefreshCategories)
    }

    fun onEvent(event: CategoriaUiEvent) {
        when (event) {
            is CategoriaUiEvent.LoadCategories -> {
                viewModelScope.launch {
                    categoriaUseCases.getCategoria().collect { list ->
                        _state.update { it.copy(categories = list) }
                    }
                }
            }
            is CategoriaUiEvent.RefreshCategories -> {
                viewModelScope.launch {
                    _state.update { it.copy(isLoading = true) }
                    categoriaUseCases.refreshCategoria()
                    _state.update { it.copy(isLoading = false) }
                }
            }
        }
    }
}