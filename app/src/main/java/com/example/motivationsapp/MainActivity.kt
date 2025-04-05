package com.example.motivationsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import com.example.motivationsapp.R

class MainActivity : AppCompatActivity() {
    
    private val quotes = listOf( // A list with motivational quotes
        "Du klarer det!",
        "En dag ad gangen.",
        "Du er sejere end du tror.",
        "Lad ikke frygten styre dig.",
        "Husk at trække vejret.",
        "Fremtiden begynder nu.",
        "Tro på, at du kan, og du er halvvejs - Theodore Roosevelt",
        "Du er modigere end du tror, stærkere end du ser ud til, og klogere end du ved - Peter Plys",
        "Hvis du bliver træt, skal du lære at hvile, ikke at give op - Banksy",
        "Du har styrken i dig."
    )

    override fun onCreate(savedInstanceState: Bundle?) { // when the app is created 
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // the layout file for the main activity

        val quoteTextView = findViewById<TextView>(R.id.quoteTextView) // TextView to display the quote
        val generateButton = findViewById<Button>(R.id.generateButton) // Button to generate a new quote

        generateButton.setOnClickListener { // when the button is clicked
            val randomQuote = quotes.random() // Get a random quote from the list
            quoteTextView.text = randomQuote // Set the random quote to the TextView
        }
    }
}