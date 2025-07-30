package ru.easycode.zerotoheroandroidtdd

interface Repository {

    interface Read {
        fun list(): List<Item>
    }

    interface Add {
        fun add(value: String) : Long
    }

    interface ReadItem {
        fun item(id: Long) : Item
    }

    interface DeleteItem {
        fun delete(id: Long)
    }
    interface Delete: ReadItem, DeleteItem
    interface All:  Read, Add, Delete

    class Base  (
        private val dataSource: ItemsDao,
        private val now: Now

    ) : All {
        override fun list(): List<Item> =  dataSource.list().map { it.map() }

        override fun add(value: String): Long {
            val id = now.nowMillis()
            val item = ItemCache(id = id, text = value)
            dataSource.add(item)
            return id
        }

        override fun item(id: Long): Item = dataSource.item(id).map()

        override fun delete(id: Long) {
           dataSource.delete(id)
        }
    }
}
