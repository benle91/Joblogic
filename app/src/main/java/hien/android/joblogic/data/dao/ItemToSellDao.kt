package hien.android.joblogic.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import hien.android.joblogic.data.model.entity.ItemToSell

@Dao
interface ItemToSellDao {

    @Query("SELECT * FROM ItemToSell")
    suspend fun getAll(): List<ItemToSell>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg items: ItemToSell): Long
}