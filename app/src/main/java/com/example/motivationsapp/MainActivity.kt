package com.example.motivationapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import com.example.motivationsapp.R

class MainActivity : AppCompatActivity() {

    private val quotes = listOf(
        "Du klarer det!",
        "En dag ad gangen.",
        "Du er sejere end du tror.",
        "Lad ikke frygten styre dig.",
        "Husk at trække vejret.",
        "Fremtiden begynder nu.",
        "Du har styrken i dig."
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val quoteTextView = findViewById<TextView>(R.id.quoteTextView)
        val generateButton = findViewById<Button>(R.id.generateButton)

        generateButton.setOnClickListener {
            val randomQuote = quotes.random()
            quoteTextView.text = randomQuote
        }
    }
}