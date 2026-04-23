package edu.ucne.licorapp.presentation.navegation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import edu.ucne.licorapp.data.local.datastore.SessionDataStore
import edu.ucne.licorapp.presentation.admin.AdminScreen
import edu.ucne.licorapp.presentation.carrito.CarritoScreen
import edu.ucne.licorapp.presentation.categoria.CategoriaScreen
import edu.ucne.licorapp.presentation.checkout.CheckoutScreen
import edu.ucne.licorapp.presentation.detalle.MercanciaDetalleScreen
import edu.ucne.licorapp.presentation.inicio.InicioScreen
import edu.ucne.licorapp.presentation.login.LoginScreen
import edu.ucne.licorapp.presentation.navegation.Appnavhost.Routes
import edu.ucne.licorapp.presentation.perfil.PerfilScreen
import edu.ucne.licorapp.presentation.registro.RegistrarScreen
import edu.ucne.licorapp.presentation.ordenar.OrdenarHistoriaScreen
import edu.ucne.licorapp.presentation.ordenar.OrdenarDetalleScreen

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
    sessionDataStore: SessionDataStore
) {
    val isLoggedIn by sessionDataStore.isLoggedIn.collectAsState(initial = null)

    if (isLoggedIn == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        return
    }

    val startDest = if (isLoggedIn == true) Routes.Home.route else Routes.Login.route

    NavHost(navController = navController, startDestination = startDest) {

        composable(Routes.Login.route) {
            LoginScreen(
                onNavigateToHome = {
                    navController.navigate(Routes.Home.createRoute()) {
                        popUpTo(Routes.Login.route) { inclusive = true }
                    }
                },
                onNavigateToRegister = {
                    navController.navigate(Routes.Register.route)
                }
            )
        }

]        composable(Routes.Register.route) {
            RegistrarScreen(onNavigateToLogin = { navController.popBackStack() })
        }

        composable(
            route = Routes.Home.route,
            arguments = listOf(
                navArgument("categoryName") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                }
            )
        ) {
            InicioScreen(
                onNavigateToDetail = { id: Int ->
                    navController.navigate(Routes.ProductDetail.createRoute(id))
                },
                onNavigateToAdmin = { navController.navigate(Routes.Admin.route) },
                onNavigateToCart = { navController.navigate(Routes.Cart.route) },
                onNavigateToProfile = { navController.navigate(Routes.Profile.route) },
                onNavigateToCategories = { navController.navigate(Routes.Categories.route) },
                onNavigateToFavorites = { navController.navigate(Routes.Favorites.route) },
                onClearFilter = {
                    navController.navigate(Routes.Home.createRoute()) {
                        popUpTo(Routes.Home.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.Categories.route) {
            CategoriaScreen(
                onNavigateBack = { navController.popBackStack() },
                onCategoriaClick = { nombre: String ->
                    navController.navigate(Routes.Home.createRoute(nombre)) {
                        popUpTo(Routes.Home.route) { inclusive = true }
                    }
                }
            )
        }

        composable(
            route = Routes.ProductDetail.route,
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) {
            MercanciaDetalleScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(Routes.Cart.route) {
            CarritoScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToCheckout = {
                    navController.navigate(Routes.Checkout.route)
                }
            )
        }

        composable(Routes.Checkout.route) {
            CheckoutScreen(
                onNavigateBack = { navController.popBackStack() },
                onPaymentSuccess = {
                    navController.navigate(Routes.Success.route) {
                        popUpTo(Routes.Cart.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.Success.route) {
            SuccessScreen(
                onGoToHome = {
                    navController.navigate(Routes.Home.createRoute()) {
                        popUpTo(Routes.Home.route) { this.inclusive = true }
                    }
                }
            )
        }

        composable(Routes.Profile.route) {
            PerfilScreen(sessionDataStore = sessionDataStore) {
                navController.navigate(Routes.Login.route) {
                    popUpTo(0) { inclusive = true }
                }
            }
        }

        composable(Routes.OrderHistory.route) {
            OrdenarHistoriaScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToOrders = { id: Int ->
                    navController.navigate(Routes.OrderDetail.createRoute(id))
                }
            )
        }

        composable(
            route = Routes.OrderDetail.route,
            arguments = listOf(navArgument("ordenarId") { type = NavType.IntType })
        ) { backStackEntry ->
            val ordenarId = backStackEntry.arguments?.getInt("ordenarId") ?: return@composable

            OrdenarDetalleScreen(
                ordenarId = ordenarId,
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(Routes.Admin.route) {
            AdminScreen(onNavigateBack = { navController.popBackStack() })
        }
    }
}