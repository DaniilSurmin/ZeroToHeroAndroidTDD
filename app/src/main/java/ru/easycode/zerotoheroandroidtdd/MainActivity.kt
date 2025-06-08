package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {


    private val count = Count.Base(2)
    private lateinit var textView: TextView
    private lateinit var button: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.countTextView)
        button = findViewById(R.id.incrementButton)

        button.setOnClickListener {
            textView.text = count.increment(textView.text.toString())
        }
    }
}

interface Count {

    fun increment(number: String): String

    class Base(private val step: Int) : Count {

        init {
           if (step < 1)
            throw IllegalStateException("step should be positive, but was $step")
        }

        override fun increment(number: String) = (step + number.toInt()).toString()
    }
}