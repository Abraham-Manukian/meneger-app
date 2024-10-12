package net.nomia.common.data.database


import androidx.room.Room
import androidx.room.RoomDatabase
import net.nomia.common.data.dao.StoreDao
import net.nomia.common.data.model.StoreEntity
import java.util.UUID

@Database(entities = [StoreEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun storeDao(): StoreDao
}
