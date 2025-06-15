package ru.easycode.zerotoheroandroidtdd

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var uiState : UiState = UiState.Initial

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.actionButton.setOnClickListener {
            uiState = UiState.ChangeText
            uiState.apply(binding.inputEditText, binding.actionButton, binding.titleTextView)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(KEY, uiState)
    }


    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            uiState = savedInstanceState.getSerializable(KEY, UiState::class.java) as UiState
        }
            savedInstanceState.getSerializable(KEY) as UiState
        uiState.apply(binding.inputEditText, binding.actionButton, binding.titleTextView)
    }

    companion object {
        private const val KEY = "uiStateKey"
    }
}



