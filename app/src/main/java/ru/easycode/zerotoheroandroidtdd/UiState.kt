package ru.easycode.zerotoheroandroidtdd

import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible

interface UiState {

    fun apply (textView: TextView, button: Button, progressBar: ProgressBar)

    object ShowProgress : UiState {

        override fun apply(textView: TextView, button: Button, progressBar: ProgressBar) {
            progressBar.isVisible = true
            textView.isVisible = false
            button.isEnabled = false
        }
    }

    object ShowData : UiState {

        override fun apply(textView: TextView, button: Button, progressBar: ProgressBar) {
            progressBar.isVisible = false
            textView.isVisible = true
            button.isEnabled = true
        }
    }
}