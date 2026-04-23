package edu.ucne.licorapp.presentation.categoria

sealed class CategoriaUiEvent {
    object LoadCategories : CategoriaUiEvent()
    object RefreshCategories : CategoriaUiEvent()
}