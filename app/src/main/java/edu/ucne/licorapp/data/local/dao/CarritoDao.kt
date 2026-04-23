package edu.ucne.licorapp.data.local.dao
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import edu.ucne.licorapp.data.local.entity.CarritoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CarritoDao {
    @Query("SELECT * FROM cart_items")
    fun getCartItemsFlow(): Flow<List<CarritoEntity>>

    @Query("SELECT * FROM carritoitems WHERE isSynced = 0")
    suspend fun getUnsyncedItems(): List<CarritoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(item: CarritoEntity)

    @Query("UPDATE cart_items SET isSynced = 1 WHERE productoId = :productoId")
    suspend fun markAsSynced(productoId: Int)

    @Query("DELETE FROM cart_items WHERE productoId = :productoId")
    suspend fun deleteItem(productoId: Int)

    @Query("SELECT * FROM cart_items")
    suspend fun getCartOnce(): List<CarritoEntity>

    @Query("DELETE FROM cart_items")
    suspend fun clearCart()
}