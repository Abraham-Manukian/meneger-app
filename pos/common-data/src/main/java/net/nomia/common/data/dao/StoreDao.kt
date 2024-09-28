package net.nomia.common.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import java.util.UUID

@Dao
interface StoreDao {

    @Insert
    suspend fun insertStore(store: StoreEntity)

    @Update
    suspend fun updateStore(store: StoreEntity)

    @Query("SELECT * FROM store WHERE id = :storeId")
    suspend fun getStoreById(storeId: UUID): StoreEntity?

    @Query("DELETE FROM store WHERE id = :storeId")
    suspend fun deleteStore(storeId: UUID)
}