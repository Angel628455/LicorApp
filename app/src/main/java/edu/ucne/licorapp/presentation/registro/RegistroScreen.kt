package edu.ucne.licorapp.presentation.registro

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun RegistrarScreen(
    onNavigateToLogin: () -> Unit,
    viewModel: RegistroViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(state.isSuccess) {
        if (state.isSuccess) onNavigateToLogin()
    }

    LaunchedEffect(state.errorMessage) {
        state.errorMessage?.let { error ->
            snackbarHostState.showSnackbar(message = error)
            viewModel.onEvent(RegistroUiEvent.LimpiarError)
        }
    }

    RegistroScreenContent(
        state = state,
        snackbarHostState = snackbarHostState,
        onEvent = viewModel::onEvent,
        onNavigateToLogin = onNavigateToLogin
    )
}

@Composable
fun RegistroScreenContent(
    state: RegistroUiState,
    snackbarHostState: SnackbarHostState,
    onEvent: (RegistroUiEvent) -> Unit,
    onNavigateToLogin: () -> Unit
) {
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Crear Cuenta",
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(
                value = state.nombre, onValueChange = { onEvent(RegistroUiEvent.OnNombreChanged(it)) },
                label = { Text("Nombre completo") }, leadingIcon = { Icon(Icons.Default.Person, contentDescription = "Nombre") }, modifier = Modifier.fillMaxWidth(), singleLine = true, shape = MaterialTheme.shapes.medium
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = state.email, onValueChange = { onEvent(RegistroUiEvent.OnEmailChanged(it)) },
                label = { Text("Correo electrónico") }, leadingIcon = { Icon(Icons.Default.Email, contentDescription = "Email") }, keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                ), modifier = Modifier.fillMaxWidth(), singleLine = true, shape = MaterialTheme.shapes.medium
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = state.password, onValueChange = { onEvent(RegistroUiEvent.OnPasswordChanged(it)) },
                label = { Text("Contraseña") }, leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Password") }, visualTransformation = PasswordVisualTransformation(), keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password), modifier = Modifier.fillMaxWidth(), singleLine = true, shape = MaterialTheme.shapes.medium
            )
            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = { onEvent(RegistroUiEvent.SubmitRegistro) },
                modifier = Modifier.run { fillMaxWidth().height(50.dp) }, shape = MaterialTheme.shapes.medium, enabled = !state.isLoading
            ) {
                if (state.isLoading) {
                    CircularProgressIndicator(color = MaterialTheme.colorScheme.onPrimary, modifier = Modifier.size(24.dp), strokeWidth = 2.dp)
                } else {
                    Text("Registrarme", style = MaterialTheme.typography.titleMedium)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Text(text = "¿Ya tienes una cuenta? ", color = MaterialTheme.colorScheme.onSurfaceVariant)
                Text(text = "Inicia Sesión", color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold, modifier = Modifier.clickable { onNavigateToLogin() })
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegistrarUiScreen() {
    MaterialTheme {
        RegistroScreenContent(
            state = RegistroUiState(),
            snackbarHostState = remember { SnackbarHostState() },
            onEvent = {},
            onNavigateToLogin = {}
        )
    }
}