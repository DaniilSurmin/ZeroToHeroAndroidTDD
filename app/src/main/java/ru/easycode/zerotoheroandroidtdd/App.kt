package ru.easycode.zerotoheroandroidtdd

import android.app.Application
import androidx.lifecycle.ViewModel

class App : Application(), ProvideViewModel {

    private lateinit var factory: ProvideViewModel
    private val store = HashMap<Class<out ViewModel>, ViewModel?>()

    private val clear = object : ClearViewModel {
        override fun clearViewModel(clasz: Class<out ViewModel>) {
            store[clasz] = null
        }
    }

    override fun onCreate() {
        super.onCreate()
        val core = Core(this)
        factory = ProvideViewModel.Base(core, clear)
    }

    override fun <T : ViewModel> viewModel(clasz: Class<T>): T {
        if (store[clasz] == null)
            store[clasz] = factory.viewModel(clasz)
        return store[clasz] as T
    }
}

interface ProvideViewModel {

    fun <T : ViewModel> viewModel(clasz: Class<T>): T

    class Base(core: Core, private val clear: ClearViewModel) : ProvideViewModel {

        private val repository = Repository.Base(core.dao(), Now.Base())
        private val liveDataWrapper = ListLiveDataWrapper.Base()

        override fun <T : ViewModel> viewModel(clasz: Class<T>): T =
            when (clasz) {
                MainViewModel::class.java -> MainViewModel(repository, liveDataWrapper)
                AddViewModel::class.java -> AddViewModel(repository, liveDataWrapper, clear)
                DeleteViewModel::class.java -> DeleteViewModel(clear, liveDataWrapper, repository)
                else -> throw IllegalStateException("Unknown ViewModel class: ${clasz.name}")
            } as T
    }
}