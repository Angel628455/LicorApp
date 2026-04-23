package edu.ucne.licorapp.presentation.admin
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import edu.ucne.licorapp.domain.model.Mercancia

@Composable
fun AdminScreenView(
    onNavigateBack: () -> Unit,
    viewModel: AdminViewModel = hiltViewModel()
)
{
    val state by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(state.isSuccess) {
        if (state.isSuccess) {
            val mensaje = state.successMessage.ifEmpty { "¡Operación exitosa!" }
            snackbarHostState.showSnackbar(mensaje)
            viewModel.onEvent(AdminUiEvent.ResetSuccess)
        }
    }

    LaunchedEffect(state.errorMessage) {
        state.errorMessage?.let { error ->
            snackbarHostState.showSnackbar(message = error)
            viewModel.onEvent(AdminUiEvent.LimpiarError)
        }
    }

    AdminScreenContent(
        state = state,
        snackbarHostState = snackbarHostState,
        onEvent = viewModel::onEvent,
        onNavigateBack = onNavigateBack
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminScreenContent(
    state: AdminUiState,
    snackbarHostState: SnackbarHostState,
    onEvent: (AdminUiEvent) -> Unit,
    onNavigateBack: () -> Unit
) {
    val scrollState = rememberScrollState()
    var mostrarDialogoBorrar by remember { mutableStateOf(false) }
    var mercanciaIdParaBorrar by remember { mutableStateOf<Int?>(null) }

    if (mostrarDialogoBorrar && mercanciaIdParaBorrar != null) {
        AlertDialog(
            onDismissRequest = {
                mostrarDialogoBorrar = false
                mercanciaIdParaBorrar = null
            },
            title = { Text("Eliminar mercancia", fontWeight = FontWeight.Bold) },
            text = { Text("¿Estás seguro de que deseas eliminar esta mercancia?.") },
            confirmButton = {
                TextButton(onClick = {
                    onEvent(AdminUiEvent.OnDeleteClicked(mercanciaIdParaBorrar!!))
                    mostrarDialogoBorrar = false
                    mercanciaIdParaBorrar = null
                }) {
                    Text("Sí, Eliminar", color = Color.Red, fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    mostrarDialogoBorrar = false
                    mercanciaIdParaBorrar = null
                }) {
                    Text("Cancelar")
                }
            }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (state.isEditMode) "Editar Mercancia" else "Agregar Mercancia") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) { Icon(Icons.Default.ArrowBack, contentDescription = "Volver") }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = state.nombre, onValueChange = { onEvent(AdminUiEvent.OnNombreChanged(it)) },
                label = { Text("Nombre de Mercancia") }, modifier = Modifier.fillMaxWidth(), singleLine = true
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = state.marca, onValueChange = { onEvent(AdminUiEvent.OnMarcaChanged(it)) },
                label = { Text("Marca (ej: Mack Albert, King Pride)") }, modifier = Modifier.fillMaxWidth(), singleLine = true
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = state.categoriaId, onValueChange = { onEvent(AdminUiEvent.OnCategoriaIdChanged(it)) },
                label = { Text("ID de la Categoría (Número)") }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), modifier = Modifier.fillMaxWidth(), singleLine = true
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = state.precio, onValueChange = { onEvent(AdminUiEvent.OnPrecioChanged(it)) },
                label = { Text("Precio (USD)") }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal), modifier = Modifier.fillMaxWidth(), singleLine = true
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = state.stock, onValueChange = { onEvent(AdminUiEvent.OnStockChanged(it)) },
                label = { Text("Cantidad en Stock") }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), modifier = Modifier.fillMaxWidth(), singleLine = true
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = state.imagenUrl, onValueChange = { onEvent(AdminUiEvent.OnImagenUrlChanged(it)) },
                label = { Text("URL de la Imagen") }, modifier = Modifier.fillMaxWidth(), singleLine = true
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = state.descripcion, onValueChange = { onEvent(AdminUiEvent.OnDescripcionChanged(it)) },
                label = { Text("Descripción") }, modifier = Modifier.fillMaxWidth(), minLines = 3, maxLines = 5
            )
            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { onEvent(AdminUiEvent.SubmitMercancia) },
                modifier = Modifier.fillMaxWidth().height(50.dp),
                enabled = !state.isLoading
            ) {
                if (state.isLoading) {
                    CircularProgressIndicator(color = MaterialTheme.colorScheme.onPrimary, modifier = Modifier.size(24.dp), strokeWidth = 2.dp)
                } else {
                    Text(text = if (state.isEditMode) "Guardar Cambios" else "Subir Mercancia", style = MaterialTheme.typography.titleMedium)
                }
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = 24.dp))

            Text(
                text = "Catálogo de Mercancia",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Start)
            )
            Spacer(modifier = Modifier.height(16.dp))

            state.mercancia.forEach { mercancia ->
                MercanciaAdminItem(
                    mercancia = mercancia,
                    onEditClick = { onEvent(AdminUiEvent.OnEditClicked(mercancia.id)) },
                    onDeleteClick = {
                        mercanciaIdParaBorrar = mercancia.id
                        mostrarDialogoBorrar = true
                    }
                )
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun MercanciaAdminItem(mercancia: Mercancia, onEditClick: (Int) -> Unit, onDeleteClick: (Int) -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(modifier = Modifier.fillMaxWidth().padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = mercancia.imagenUrl, contentDescription = mercancia.nombre,
                modifier = Modifier.size(70.dp).clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = mercancia.nombre, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, maxLines = 1, overflow = TextOverflow.Ellipsis)
                Text(text = "$${mercancia.precio}", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.primary)
            }
            Row {
                IconButton(onClick = { onEditClick(mercancia.id) }) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "Editar", tint = MaterialTheme.colorScheme.primary)
                }
                IconButton(onClick = { onDeleteClick(mercancia.id) }) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Eliminar", tint = Color.Red)
                }
            }
        }
    }
}
