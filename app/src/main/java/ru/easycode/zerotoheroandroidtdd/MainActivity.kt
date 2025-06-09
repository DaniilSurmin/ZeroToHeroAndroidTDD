package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding
import java.io.Serializable

class MainActivity : AppCompatActivity() {

    private lateinit var uiState: UiState

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        uiState = UiState.Initial
        uiState.apply(binding)

        binding.actionButton.setOnClickListener {
            uiState = UiState.Load
            uiState.apply(binding)
            it.postDelayed({
                uiState = UiState.Success
                uiState.apply(binding)
            }, 3000)
        }
    }
}

interface UiState : Serializable{

    fun apply(binding: ActivityMainBinding)

    object Initial : UiState {
        override fun apply(binding: ActivityMainBinding) = with (binding) {
            progressBar.isVisible = false
            titleTextView.isVisible = false
            actionButton.isEnabled = true
        }
    }

    object Load : UiState {
        override fun apply(binding: ActivityMainBinding) = with (binding)  {
            progressBar.isVisible = true
            titleTextView.isVisible = false
            actionButton.isEnabled = false
            actionButton.isVisible = false
        }
    }

    object Success : UiState {
        override fun apply(binding: ActivityMainBinding) = with (binding) {
            progressBar.isVisible = false
            titleTextView.isVisible = true
            actionButton.isEnabled = true
            actionButton.isVisible = true
        }
    }
}