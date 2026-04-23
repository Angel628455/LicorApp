package edu.ucne.licorapp.presentation.admin

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminScreen(
    onNavigateBack: () -> Unit,
    viewModel: AdminViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Admin") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Text("<")
                    }
                }
            )
        }
    ) { padding ->

        Column(modifier = Modifier.padding(padding).padding(16.dp)) {

            OutlinedTextField(
                value = state.nombre,
                onValueChange = { viewModel.onEvent(AdminUiEvent.OnNombreChanged(it)) },
                label = { Text("Nombre") }
            )

            OutlinedTextField(
                value = state.precio,
                onValueChange = { viewModel.onEvent(AdminUiEvent.OnPrecioChanged(it)) },
                label = { Text("Precio") }
            )

            OutlinedTextField(
                value = state.stock,
                onValueChange = { viewModel.onEvent(AdminUiEvent.OnStockChanged(it)) },
                label = { Text("Stock") }
            )

            Button(
                onClick = { viewModel.onEvent(AdminUiEvent.SubmitMercancia) },
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text("Guardar")
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn {
                items(state.mercancia) { item ->
                    Text(item.nombre)
                }
            }
        }
    }
}