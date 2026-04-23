package edu.ucne.licorapp.data.local.database
import androidx.room.Database
import androidx.room.RoomDatabase
import edu.ucne.licorapp.data.local.dao.CarritoDao
import edu.ucne.licorapp.data.local.dao.CategoriaDao
import edu.ucne.licorapp.data.local.dao.MercanciaDao
import edu.ucne.licorapp.data.local.dao.OrdenarDao
import edu.ucne.licorapp.data.local.entity.CarritoEntity
import edu.ucne.licorapp.data.local.entity.CategoriaEntity
import edu.ucne.licorapp.data.local.entity.MercanciaEntity
import edu.ucne.licorapp.data.local.entity.OrdenarDetailEntity
import edu.ucne.licorapp.data.local.entity.OrdenarEntity

@Database(
    entities = [
        MercanciaEntity::class,
        CarritoEntity::class,
        CategoriaEntity::class,
        OrdenarEntity::class,
        OrdenarDetailEntity::class
    ],
    version = 5,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract val mercanciaDao: MercanciaDao
    abstract val carritoDao: CarritoDao

    abstract val categoriaDao: CategoriaDao
    abstract val ordenarDao: OrdenarDao
}