package hien.android.joblogic.core.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import hien.android.joblogic.data.dao.ItemToSellDao
import hien.android.joblogic.data.model.entity.ItemToSell

@Database(entities = [ItemToSell::class], version = 1, exportSchema = false)
@TypeConverters(BigDecimalTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getItemToSellDao(): ItemToSellDao
}