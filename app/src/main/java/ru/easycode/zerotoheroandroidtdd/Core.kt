package ru.easycode.zerotoheroandroidtdd

import android.content.Context
import androidx.room.Room

class Core (context: Context){

    private val dataBase: ItemsDataBase by lazy {
        Room.databaseBuilder(
            context,
            ItemsDataBase::class.java,
            "item_database"
        ).build()
    }

    fun dao() = dataBase.itemsDao()
}