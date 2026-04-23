package edu.ucne.licorapp.di
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import edu.ucne.licorapp.data.local.datastore.SessionDataStore
import edu.ucne.licorapp.domain.repository.AutentificarRepository
import edu.ucne.licorapp.domain.repository.CarritoRepository
import edu.ucne.licorapp.domain.repository.CategoriaRepository
import edu.ucne.licorapp.domain.repository.FavoriteRepository
import edu.ucne.licorapp.domain.repository.MercanciaRepository
import edu.ucne.licorapp.domain.repository.OrdenarRepository
import edu.ucne.licorapp.domain.usecase.carritousecase.AddToCarritoUseCase
import edu.ucne.licorapp.domain.usecase.carritousecase.CarritoUseCases
import edu.ucne.licorapp.domain.usecase.carritousecase.DecrementQuantityUseCase
import edu.ucne.licorapp.domain.usecase.carritousecase.GetCarritoUseCase
import edu.ucne.licorapp.domain.usecase.carritousecase.RemoverFromCarritoUseCase
import edu.ucne.licorapp.domain.usecase.carritousecase.SyncCartUseCase
import edu.ucne.licorapp.domain.usecase.categoriausecase.CategoriaUseCases
import edu.ucne.licorapp.domain.usecase.favoriteusecase.FavoriteUseCases
import edu.ucne.licorapp.domain.usecase.favoriteusecase.GetFavoritesUseCase
import edu.ucne.licorapp.domain.usecase.favoriteusecase.RefreshFavoritesUseCase
import edu.ucne.licorapp.domain.usecase.favoriteusecase.ToggleFavoriteUseCase
import edu.ucne.licorapp.domain.usecase.loginusecase.AutentificarUseCase
import edu.ucne.licorapp.domain.usecase.loginusecase.LoginUseCase
import edu.ucne.licorapp.domain.usecase.loginusecase.LogoutUseCase
import edu.ucne.licorapp.domain.usecase.loginusecase.RegistrarUseCase
import edu.ucne.licorapp.domain.usecase.mercanciausecase.BuscarMercanciaUseCase
import edu.ucne.licorapp.domain.usecase.mercanciausecase.CreateMercanciaUseCase
import edu.ucne.licorapp.domain.usecase.mercanciausecase.GetMercanciaUseCase
import edu.ucne.licorapp.domain.usecase.mercanciausecase.MercanciaUseCases
import edu.ucne.licorapp.domain.usecase.ordenarusecase.CreateOrdenarUseCase
import edu.ucne.licorapp.domain.usecase.ordenarusecase.GetOrdenarHistoryUseCase
import edu.ucne.licorapp.domain.usecase.ordenarusecase.OrdenarUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideMercanciaCases(repository: MercanciaRepository): MercanciaUseCases {
        return MercanciaUseCases(
            getMercancia = GetMercanciaUseCase(repository),
            getMercanciaById = GetMercanciaUseCase(repository),
            refreshMercancia = RefreshMercanciaUseCase(repository),
            buscarMercancia = BuscarMercanciaUseCase(repository),
            createMercancia = CreateMercanciaUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun provideAutentificarUseCases(
        repository: AutentificarRepository,
        sessionDataStore: SessionDataStore
    ): AutentificarUseCase {
        return AutentificarUseCase(
            login = LoginUseCase(repository, sessionDataStore),
            register = RegistrarUseCase(repository),
            logout = LogoutUseCase(sessionDataStore)
        )
    }

    @Provides
    @Singleton
    fun provideCarritoUseCases(repository: CarritoRepository): CarritoUseCases {
        return CarritoUseCases(
            getCart = GetCarritoUseCase(repository),
            addToCart = AddToCarritoUseCase(repository),
            removeFromCart = RemoverFromCarritoUseCase(repository),
            syncCart = SyncCartUseCase(repository),
            decrementQuantity = DecrementQuantityUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun provideCategoriaUseCases(repository: CategoriaRepository): CategoriaUseCases {
        return CategoriaUseCases(
            getCategories = GetCategoriesUseCase(repository),
            refreshCategories = RefreshCategoriesUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun provideFavoriteUseCases(repository: FavoriteRepository): FavoriteUseCases {
        return FavoriteUseCases(
            getFavorites = GetFavoritesUseCase(repository),
            toggleFavorite = ToggleFavoriteUseCase(repository),
            refreshFavorites = RefreshFavoritesUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun provideOrdenarUseCases(repository: OrdenarRepository): OrdenarUseCase {
        return OrdenarUseCase(
            getOrderHistory = GetOrdenarHistoryUseCase(repository),
            refreshOrderHistory = RefreshOrdenarHistoryUseCase(repository),
            createOrder = CreateOrdenarUseCase(repository)
        )
    }
}