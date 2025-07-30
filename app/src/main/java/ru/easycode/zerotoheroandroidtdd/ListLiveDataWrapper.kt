package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface ListLiveDataWrapper<T> {

    interface Update {
        fun update(value: List<ItemUi>)
    }

    interface Read {
        fun liveData(): LiveData<List<ItemUi>>
    }

    interface Add{
        fun add(value: ItemUi)
    }

    interface Delete {
        fun delete(item: ItemUi)
    }

    interface Mutable : Read, Update

    interface All : Mutable, Add, Delete

    class Base : All {

        private val liveData = MutableLiveData<List<ItemUi>>()
        private val currentList = liveData.value?.toMutableList() ?: mutableListOf()

        override fun liveData(): LiveData<List<ItemUi>> = liveData

        override fun update(value: List<ItemUi>) {
           liveData.postValue(value)
        }

        override fun add(value: ItemUi) {
            currentList.add(value)
            update(currentList)
        }

        override fun delete(item: ItemUi) {
            currentList.remove(item)
            update(currentList)
        }
    }
}
