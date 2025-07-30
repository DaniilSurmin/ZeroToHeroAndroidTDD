package ru.easycode.zerotoheroandroidtdd

data class Item (val id: Long, val text: String)

fun ItemCache.map(): Item = Item(id = this.id, text = this.text)
fun Item.map(): ItemUi = ItemUi(id = this.id, text = this.text)

