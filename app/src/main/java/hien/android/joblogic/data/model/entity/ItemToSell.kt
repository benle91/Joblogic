package hien.android.joblogic.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal

@Entity(tableName = "ItemToSell")
data class ItemToSell(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int?,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "price") val price: String?,
    @ColumnInfo(name = "quantity") val quantity: Int?,
    @ColumnInfo(name = "type") val type: Int?
)