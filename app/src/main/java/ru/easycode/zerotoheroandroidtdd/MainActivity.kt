package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private val count: Count = Count.Base(2, 4, 0)
    private lateinit var uiState: UiState

    private lateinit var textView: TextView
    private lateinit var incrementButton: Button
    private lateinit var decrementButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.countTextView)
        incrementButton = findViewById(R.id.incrementButton)
        decrementButton = findViewById(R.id.decrementButton)

        incrementButton.setOnClickListener {
            uiState = count.increment(textView.text.toString())
            uiState.apply(decrementButton, incrementButton, textView)
        }

        decrementButton.setOnClickListener {
            uiState = count.decrement(textView.text.toString())
            uiState.apply(decrementButton, incrementButton, textView)
        }

        if (savedInstanceState == null) {
            uiState = count.initial(textView.text.toString())
            uiState.apply(decrementButton, incrementButton, textView)
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        uiState = count.initial(textView.text.toString())
        uiState.apply(decrementButton, incrementButton, textView)
    }
}