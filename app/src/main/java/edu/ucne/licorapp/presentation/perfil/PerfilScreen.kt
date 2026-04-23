package edu.ucne.licorapp.presentation.perfil

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.ucne.licorapp.data.local.datastore.SessionDataStore
import kotlinx.coroutines.launch

@Composable
fun PerfilScreen(
    sessionDataStore: SessionDataStore,
    onNavigateBack: () -> Unit,
    onNavigateToOrders: () -> Unit,
    onLogout: () -> Unit
) {
    val name by sessionDataStore.getUserName().collectAsState(initial = "")
    val email by sessionDataStore.getUserEmail().collectAsState(initial = "")
    val role by sessionDataStore.getUserRole().collectAsState(initial = "Cliente")
    val scope = rememberCoroutineScope()

    PerfilScreenContent(
        name = name,
        email = email,
        role = role,
        onNavigateBack = onNavigateBack,
        onNavigateToOrders = onNavigateToOrders,
        onLogout = {
            scope.launch {
                sessionDataStore.clearSession()
                onLogout()
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerfilScreenContent(
    name: String,
    email: String,
    role: String,
    onNavigateBack: () -> Unit,
    onNavigateToOrders: () -> Unit,
    onLogout: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mi Cuenta", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) { Icon(Icons.Default.ArrowBack, contentDescription = "Atrás") }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier.size(100.dp).clip(CircleShape).background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = if (name.isNotBlank()) name.take(1).uppercase() else "?",
                    style = MaterialTheme.typography.displayMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(text = name.ifBlank { "Usuario" }, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
            Text(text = email.ifBlank { "ejemplo@mail.com" }, style = MaterialTheme.typography.bodyLarge, color = Color.Gray)

            val isAdmin = role.equals("Admin", ignoreCase = true)
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = if (isAdmin) Color(0xFFFFEBEE) else Color(0xFFE8F5E9),
                modifier = Modifier.padding(top = 12.dp)
            ) {
                Text(
                    text = role.uppercase(),
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp),
                    style = MaterialTheme.typography.labelLarge,
                    color = if (isAdmin) Color(0xFFD32F2F) else Color(0xFF2E7D32),
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            OutlinedButton(
                onClick = onNavigateToOrders, modifier = Modifier.fillMaxWidth().height(56.dp), shape = RoundedCornerShape(16.dp)
            ) {
                Icon(Icons.Default.List, contentDescription = null)
                Spacer(modifier = Modifier.width(10.dp))
                Text("MIS PEDIDOS", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = onLogout,
                modifier = Modifier.fillMaxWidth().height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error), shape = RoundedCornerShape(16.dp)
            ) {
                Icon(Icons.Default.ExitToApp, contentDescription = null)
                Spacer(modifier = Modifier.width(10.dp))
                Text("CERRAR SESIÓN", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Preview(showBackground = true, name = "1. Vista de Cliente")
@Composable
fun PerfilScreenPreviewClient() {
    MaterialTheme {
        PerfilScreenContent(
            name = "Ana Josefa", email = "AnaJsf@dev.com", role = "Cliente",
            onNavigateBack = {}, onNavigateToOrders = {}, onLogout = {}
        )
    }
}

@Preview(showBackground = true, name = "2. Vista de Administrador")
@Composable
fun ProfileScreenPreviewAdmin() {
    MaterialTheme {
        PerfilScreenContent(
            name = "Super Admin", email = "admin@licorstore.com", role = "Admin",
            onNavigateBack = {}, onNavigateToOrders = {}, onLogout = {}
        )
    }
}