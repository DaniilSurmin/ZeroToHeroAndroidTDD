package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DeleteViewModel (

    private val clear: ClearViewModel,
    private val deleteLiveDataWrapper: ListLiveDataWrapper.Delete,
    private val repository: Repository.Delete,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val dispatcherMain: CoroutineDispatcher = Dispatchers.Main

) : ViewModel(){

    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    private val _livedata = MutableLiveData<String>()
    val liveData: LiveData<String> = _livedata

    fun init(itemId: Long) {
        viewModelScope.launch (dispatcher){
            val item = repository.item(itemId)
            withContext(dispatcherMain) {
                _livedata.value = item.text
            }
        }
    }

    fun delete(itemId: Long) {
        viewModelScope.launch (dispatcher) {
            repository.delete(itemId)
            withContext(dispatcherMain) {
                deleteLiveDataWrapper.delete(ItemUi(id = itemId, text = _livedata.value ?: ""))
                comeback()
            }
        }
    }

    fun comeback() {
        clear.clearViewModel(DeleteViewModel::class.java)
    }
}