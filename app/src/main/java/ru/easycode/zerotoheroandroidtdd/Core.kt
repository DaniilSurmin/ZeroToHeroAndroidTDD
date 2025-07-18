package ru.easycode.zerotoheroandroidtdd

import android.content.Context
import androidx.room.Room

class Core(private val context: Context) {

    private val database by lazy {
        Room.databaseBuilder(
            context,
            ItemsDataBase::class.java,
            "item_database"
        ).build()
    }

    fun dao() = database.itemsDao()
}