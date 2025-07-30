package ru.easycode.zerotoheroandroidtdd

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query


@Entity(tableName = "items_table")
data class ItemCache (
    @PrimaryKey @ColumnInfo (name = "id") val id: Long,
    @ColumnInfo(name = "text") val text: String
)

@Dao
interface ItemsDao {
    @Query("SELECT * FROM items_table")
    fun list(): List<ItemCache>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun add(item: ItemCache)

    @Query("SELECT * FROM items_table WHERE id = :id LIMIT 1")
    fun item(id: Long): ItemCache

    @Query("DELETE FROM items_table WHERE id = :id")
    fun delete(id: Long)
}

