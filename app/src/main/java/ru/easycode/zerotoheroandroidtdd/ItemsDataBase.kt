package ru.easycode.zerotoheroandroidtdd

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [ItemCache::class], version = 1, exportSchema = false)
abstract class ItemsDataBase : RoomDatabase() {

    abstract fun itemsDao(): ItemsDao
}
