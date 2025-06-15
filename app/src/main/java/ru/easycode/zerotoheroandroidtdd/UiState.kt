package ru.easycode.zerotoheroandroidtdd

import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import java.io.Serializable

interface UiState : Serializable{

    fun apply (textInputEditText: TextInputEditText, button: Button, textView: TextView)

    object Initial : UiState {
        override fun apply(
            textInputEditText: TextInputEditText,
            button: Button,
            textView: TextView
        ) = Unit

    }


    object ChangeText : UiState {
        override fun apply(
            textInputEditText: TextInputEditText,
            button: Button,
            textView: TextView
        ) {
            textView.text = textInputEditText.text
            textInputEditText.setText("")


        }


    }
}