package edu.ucne.licorapp.di
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import edu.ucne.licorapp.data.repository.AutentificarRepositoryImpl
import edu.ucne.licorapp.data.repository.CarritoRepositoryImpl
import edu.ucne.licorapp.data.repository.CategoriaRepositoryImpl
import edu.ucne.licorapp.data.repository.FavoriteRepositoryImpl
import edu.ucne.licorapp.data.repository.MercanciaRepositoryImpl
import edu.ucne.licorapp.data.repository.OrdenarRepositoryImpl
import edu.ucne.licorapp.domain.repository.AutentificarRepository
import edu.ucne.licorapp.domain.repository.CarritoRepository
import edu.ucne.licorapp.domain.repository.CategoriaRepository
import edu.ucne.licorapp.domain.repository.FavoriteRepository
import edu.ucne.licorapp.domain.repository.MercanciaRepository
import edu.ucne.licorapp.domain.repository.OrdenarRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositorioModule {

    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        autentificarRepositoryImpl: AutentificarRepositoryImpl
    ): AutentificarRepository

    @Binds
    @Singleton
    abstract fun bindMercanciaRepository(
        productoRepositoryImpl: MercanciaRepositoryImpl
    ): MercanciaRepository

    @Binds
    @Singleton
    abstract fun bindCarritoRepository(
        cartRepositoryImpl: CarritoRepositoryImpl
    ): CarritoRepository

    @Binds
    @Singleton
    abstract fun bindCategoriaRepository(
        categoriaRepositoryImpl: CategoriaRepositoryImpl
    ): CategoriaRepository

    @Binds
    @Singleton
    abstract fun bindFavoriteRepository(
        favoriteRepositoryImpl: FavoriteRepositoryImpl
    ): FavoriteRepository

    @Binds
    @Singleton
    abstract fun bindOrderRepository(
        ordenarRepositoryImpl: OrdenarRepositoryImpl
    ): OrdenarRepository
}