package ru.easycode.zerotoheroandroidtdd

import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import java.io.Serializable


interface UiState : Serializable {

    fun apply(textView: TextView, button: Button, progressBar: ProgressBar)

    object ShowProgress : UiState {

        override fun apply(textView: TextView, button: Button, progressBar: ProgressBar) {
            progressBar.isVisible = true
            textView.isVisible = false
            button.isEnabled = false
        }
    }

    data class ShowData(private val text: String) : UiState {

        override fun apply(textView: TextView, button: Button, progressBar: ProgressBar) {
            progressBar.isVisible = false
            textView.text = text
            textView.isVisible = true
            button.isEnabled = true
        }


    }
}